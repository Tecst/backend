package com.tecst.tecst.domain.answer.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.google.gson.JsonObject;
import com.tecst.tecst.domain.answer.ClovaSpeechClient;
import com.tecst.tecst.domain.answer.dto.request.SaveAnswerRequestDto;
import com.tecst.tecst.domain.answer.dto.request.SaveVoiceAnswerRequestDto;
import com.tecst.tecst.domain.answer.dto.response.GetAnswerResponseDto;
import com.tecst.tecst.domain.answer.dto.response.GetVoiceAnswerResponseDto;
import com.tecst.tecst.domain.answer.entity.Answer;
import com.tecst.tecst.domain.answer.exception.AnswerNotFound;
import com.tecst.tecst.domain.answer.mapper.AnswerMapper;
import com.tecst.tecst.domain.answer.repository.AnswerRepository;
import com.tecst.tecst.domain.auth.service.CustomUserDetailsService;
import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.domain.question.exception.QuestionNotFound;
import com.tecst.tecst.domain.question.repository.QuestionRepository;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.domain.user.exception.UserNotFound;
import com.tecst.tecst.domain.user.repository.UserRepository;
import com.tecst.tecst.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;
import okio.ByteString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository commonQuestionRepository;
    private final UserRepository userRepository;
    private final CustomUserDetailsService userService;

    private final UserService userService2;
    private final AnswerMapper answerMapper;
    private final ClovaSpeechClient clovaSpeechClient;
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${chatgpt.api-key}")
    private String GPT_API_KEY;

    public GetAnswerResponseDto saveAnswer(SaveAnswerRequestDto dto) {
        Question commonQuestion = commonQuestionRepository.findById(dto.getQuestionsId()).orElseThrow(QuestionNotFound::new);
        Long userId = userService2.getLoginUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        Answer answer = answerMapper.toEntity(dto, user, commonQuestion);
        answerRepository.save(answer);
        return answerMapper.toDto(answer);
    }

    public GetVoiceAnswerResponseDto GetInterviewRecord(Long id) {
        Answer result = answerRepository.findById(id).orElseThrow(AnswerNotFound::new);
        GetVoiceAnswerResponseDto dto = new GetVoiceAnswerResponseDto();
        dto.setAnswerId(id);
        dto.setAnswerURL(result.getAnswerURL());
        return dto;
    }

    public GetAnswerResponseDto getAnswer(Long answersId) {
        Answer answer = answerRepository.findById(answersId).orElseThrow(AnswerNotFound::new);
        return answerMapper.toDto(answer);
    }

    public GetAnswerResponseDto saveVoiceAnswer(SaveVoiceAnswerRequestDto dto) throws IOException, JSONException {
        Question commonQuestion = commonQuestionRepository.findById(dto.getCommonQuestionsId()).orElseThrow(QuestionNotFound::new);
        User user = userService2.getLoginUser();

        Long userId = dto.getUserId();
        Long commonQuestionsId = dto.getCommonQuestionsId();
        String fileName = dto.getMultipartFile().getOriginalFilename();
        MultipartFile multipartFile = dto.getMultipartFile();

        String answerURL = S3Upload(userId, commonQuestionsId, fileName, multipartFile);
        String text = GetSttText(multipartFile);

        Answer answer = answerMapper.UploadsToEntity(dto, user, commonQuestion, answerURL, text);
        answerRepository.save(answer);

        return answerMapper.toDto(answer);
    }

    public String S3Upload(Long userId, Long commonQuestionsId, String fileName, MultipartFile multipartFile) throws IOException {
        //업로드 파일명 설정( 폴더명 + "/" + 파일명 + "." + 확장자명 )
        int fileExtensionIndex = Objects.requireNonNull(fileName).lastIndexOf(".");
        String fileExtension = fileName.substring(fileExtensionIndex);
        String currentTime = String.valueOf(System.currentTimeMillis());

        String s3FileName = userId + "/" + commonQuestionsId + "-" + currentTime + fileExtension;
        /* or: UUID.randomUUID() + "-" + multipartFile.getOriginalFilename(); */

        // S3 업로드
        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentType("audio/mp3");
        objMeta.setContentLength(multipartFile.getInputStream().available());
        amazonS3.putObject(new PutObjectRequest(bucket, s3FileName, multipartFile.getInputStream(), objMeta)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        multipartFile.getInputStream().close();

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }

    public String GetSttText(MultipartFile multipartFile) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "audio.mp3", RequestBody.Companion.create(MediaType.parse("audio/mpeg"), multipartFile.getBytes()))
                .addFormDataPart("model", "whisper-1")
                .build();

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/audio/transcriptions")
                .post(requestBody)
                .addHeader("Authorization", "Bearer " + GPT_API_KEY)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        JSONObject jsonObject = new JSONObject(responseBody);

        return jsonObject.getString("text");
    }
}

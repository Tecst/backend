package com.tecst.tecst.domain.answer.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.tecst.tecst.domain.answer.ClovaSpeechClient;
import com.tecst.tecst.domain.answer.dto.request.SaveAnswerRequestDto;
import com.tecst.tecst.domain.answer.dto.request.SaveVoiceAnswerRequestDto;
import com.tecst.tecst.domain.answer.dto.response.GetAnswerResponseDto;
import com.tecst.tecst.domain.answer.dto.response.GetVoiceAnswerResponseDto;
import com.tecst.tecst.domain.answer.entity.Answer;
import com.tecst.tecst.domain.answer.exception.AnswerNotFound;
import com.tecst.tecst.domain.answer.mapper.AnswerMapper;
import com.tecst.tecst.domain.answer.repository.AnswerRepository;
import com.tecst.tecst.domain.common_question.entity.CommonQuestion;
import com.tecst.tecst.domain.common_question.exception.QuestionNotFound;
import com.tecst.tecst.domain.common_question.repository.CommonQuestionRepository;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.domain.user.exception.UserNotFound;
import com.tecst.tecst.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Objects;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final CommonQuestionRepository commonQuestionRepository;
    private final UserRepository userRepository;
    private final AnswerMapper answerMapper;
    private final ClovaSpeechClient clovaSpeechClient;
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    public GetAnswerResponseDto saveAnswer(SaveAnswerRequestDto dto) {
        // Type이 voice면 STT 실행
        if (dto.getType().equals("voice")) {
            ClovaSpeechClient.NestRequestEntity requestEntity = new ClovaSpeechClient.NestRequestEntity();
            final String result = clovaSpeechClient.objectStorage(dto.getResponse(), requestEntity);
            dto.setResponse(result);
        }
        CommonQuestion commonQuestion = commonQuestionRepository.findById(dto.getCommonQuestionsId()).orElseThrow(QuestionNotFound::new);
        User user = userRepository.findById(dto.getUserId()).orElseThrow(UserNotFound::new);

        Answer answer = answerMapper.toEntity(dto, user, commonQuestion);
        answerRepository.save(answer);
        return answerMapper.toDto(answer);
    }

    public GetVoiceAnswerResponseDto GetInterviewRecord(Long id) {
        Answer result = answerRepository.findById(id).orElseThrow(AnswerNotFound::new);
        GetVoiceAnswerResponseDto dto = new GetVoiceAnswerResponseDto();
        dto.setAnswerId(id);
        dto.setResponse(result.getResponse());
        return dto;
    }

    public GetAnswerResponseDto getAnswer(Long answersId) {
        Answer answer = answerRepository.findById(answersId).orElseThrow(AnswerNotFound::new);
        return answerMapper.toDto(answer);
    }

    public GetAnswerResponseDto uploadToS3(SaveVoiceAnswerRequestDto dto) throws IOException {
        CommonQuestion commonQuestion = commonQuestionRepository.findById(dto.getCommonQuestionsId()).orElseThrow(QuestionNotFound::new);
        User user = userRepository.findById(dto.getUserId()).orElseThrow(UserNotFound::new);

        Long userId = dto.getUserId();
        Long commonQuestionsId = dto.getCommonQuestionsId();
        String fileName = dto.getMultipartFile().getOriginalFilename();

        int fileExtensionIndex = Objects.requireNonNull(fileName).lastIndexOf(".");
        String fileExtension = fileName.substring(fileExtensionIndex);
        String currentTime = String.valueOf(System.currentTimeMillis());

        String s3FileName = userId + "/" + commonQuestionsId + "-" + currentTime + fileExtension;
        // UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentType("audio/wmv");
        objMeta.setContentLength(dto.getMultipartFile().getInputStream().available());
        amazonS3.putObject(new PutObjectRequest(bucket, s3FileName, dto.getMultipartFile().getInputStream(), objMeta)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        String UploadUrl = amazonS3.getUrl(bucket, s3FileName).toString();



        Answer answer = answerMapper.UploadsToEntity(dto, user, commonQuestion, UploadUrl);
        answerRepository.save(answer);

        return answerMapper.toDto(answer);
    }
}

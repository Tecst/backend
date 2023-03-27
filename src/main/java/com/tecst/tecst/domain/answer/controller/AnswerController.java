package com.tecst.tecst.domain.answer.controller;

import com.tecst.tecst.domain.answer.dto.request.SaveAnswerRequestDto;
import com.tecst.tecst.domain.answer.dto.request.SaveVoiceAnswerRequestDto;
import com.tecst.tecst.domain.answer.dto.response.GetAnswerResponseDto;
import com.tecst.tecst.domain.answer.dto.response.GetVoiceAnswerResponseDto;
import com.tecst.tecst.domain.answer.service.AnswerService;
import com.tecst.tecst.domain.common_question.entity.CommonQuestion;
import com.tecst.tecst.domain.common_question.service.CommonQuestionService;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

import static com.tecst.tecst.global.result.ResultCode.ANSWER_FIND_SUCCESS;
import static com.tecst.tecst.global.result.ResultCode.REGISTER_ANSWER_SUCCESS;

@Api(tags = "Answer API")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/interview/answers")
public class AnswerController {
    private final AnswerService answerService;
    private final CommonQuestionService commonQuestionService;

    @ApiOperation(value = "사용자가 입력한 정답 저장")
    @PostMapping("/new")
    // 답변 저장
    public ResponseEntity<ResultResponse> SaveAnswer(@RequestBody SaveAnswerRequestDto dto) {
        GetAnswerResponseDto responseDto = answerService.saveAnswer(dto);
        return ResponseEntity.ok(ResultResponse.of(REGISTER_ANSWER_SUCCESS, responseDto));
    }

   @ApiOperation(value = "녹음 파일 버킷에 전달")
    @PostMapping(value="/voice-answers/upload/new", consumes = {"multipart/form-data"})
    public ResponseEntity<ResultResponse> UploadToS3(@ModelAttribute SaveVoiceAnswerRequestDto dto) throws IOException {
        GetAnswerResponseDto responseDto = answerService.uploadToS3(dto);
        return ResponseEntity.ok(ResultResponse.of(REGISTER_ANSWER_SUCCESS, responseDto));
    }

    @ApiOperation(value = "STT 실행")
    @PostMapping("/voice-answers/new")
    // 답변 저장
    public ResponseEntity<ResultResponse> SaveVoiceAnswer(@RequestBody SaveAnswerRequestDto dto, @ApiIgnore User user) {
        CommonQuestion commonQuestion = commonQuestionService.findCommonQuestionById(dto.getCommonQuestionsId());
        answerService.saveAnswer(dto);
        return ResponseEntity.ok(ResultResponse.of(REGISTER_ANSWER_SUCCESS, dto));
    }

    @ApiOperation(value = "녹음 조회")
    @GetMapping("/voice-answers/{id}")
    public GetVoiceAnswerResponseDto GetRecord(@PathVariable Long id) {
        return answerService.GetInterviewRecord(id);
    }

    @ApiOperation(value = "사용자가 입력한 답변 가져오기")
    @GetMapping("/{answersId}")
    public ResponseEntity<ResultResponse> getAnswer(@PathVariable Long answersId) {
        GetAnswerResponseDto dto = answerService.getAnswer(answersId);
        return ResponseEntity.ok(ResultResponse.of(ANSWER_FIND_SUCCESS, dto));
    }
}

package com.tecst.tecst.domain.answer.controller;

import com.tecst.tecst.domain.answer.dto.request.SaveAnswerRequestDto;
import com.tecst.tecst.domain.answer.dto.request.SaveScoreRequestDto;
import com.tecst.tecst.domain.answer.dto.request.SaveVoiceAnswerRequestDto;
import com.tecst.tecst.domain.answer.dto.response.*;
import com.tecst.tecst.domain.answer.service.AnswerService;
import com.tecst.tecst.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.tecst.tecst.global.result.ResultCode.*;

@Api(tags = "Answer API")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/interview/answers")
public class AnswerController {
    private final AnswerService answerService;

    @ApiOperation(value = "텍스트 답변 저장")
    @PostMapping
    // 답변 저장
    public ResponseEntity<ResultResponse> SaveAnswer(@RequestBody SaveAnswerRequestDto dto) {
        GetAnswerResponseDto responseDto = answerService.saveAnswer(dto);
        return ResponseEntity.ok(ResultResponse.of(REGISTER_ANSWER_SUCCESS, responseDto));
    }

   @ApiOperation(value = "음성 답변 업로드 후 STT 실행 및 저장")
    @PostMapping(value="/voice-answers", consumes = {"multipart/form-data"})
    public ResponseEntity<ResultResponse> SaveVoiceAnswer(@ModelAttribute SaveVoiceAnswerRequestDto dto) throws IOException {
        GetAnswerResponseDto responseDto = answerService.saveVoiceAnswer(dto);
        return ResponseEntity.ok(ResultResponse.of(REGISTER_ANSWER_SUCCESS, responseDto));
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

    @ApiOperation(value = "점수 저장")
    @PostMapping("/scores")
    public ResponseEntity<ResultResponse> SaveScore(@RequestBody SaveScoreRequestDto dto) {
        GetScoreResponseDto responseDto = answerService.saveScore(dto);
        return ResponseEntity.ok(ResultResponse.of(SCORE_REGISTRATION_SUCCESS, responseDto));
    }

    @ApiOperation(value = "특정 사용자의 문제 풀이 개수 출력")
    @GetMapping("reports/solved-questions/{userId}")
    public ResponseEntity<ResultResponse> getSolvedQuestCount(@PathVariable Long userId) {
        GetSolvedQuestCountResponseDto dto = answerService.getSolvedQuestCount(userId);
        return ResponseEntity.ok(ResultResponse.of(SOLVED_COUNT_FIND_SUCCESS, dto));
    }

    @ApiOperation(value = "전체 사용자와 특정 사용자의 평균 점수 비교")
    @GetMapping("reports/{userId}")
    public ResponseEntity<ResultResponse> getAverage(@PathVariable Long userId) {
        GetAverageResponseDto dto = answerService.getAverage(userId);
        return ResponseEntity.ok(ResultResponse.of(AVERAGE_FIND_SUCCESS, dto));
    }
}

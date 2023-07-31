package com.tecst.tecst.domain.question.controller;

import com.tecst.tecst.domain.question.dto.request.CreateQuestionRequest;
import com.tecst.tecst.domain.question.dto.request.UpdateQuestionRequest;
import com.tecst.tecst.domain.question.dto.response.*;
import com.tecst.tecst.domain.question.service.dto.QuestionDTO;
import com.tecst.tecst.domain.question.service.dto.QuestionResponseDTO;
import com.tecst.tecst.global.result.PageResponse;
import com.tecst.tecst.global.result.ResultCode;
import com.tecst.tecst.global.result.ResultResponse;
import com.tecst.tecst.global.util.Type;
import com.tecst.tecst.domain.question.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Question API")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    @ApiOperation(value = "질문 등록")
    @PostMapping
    public ResponseEntity<ResultResponse> createPersonalQuestion(
            @RequestBody @Validated CreateQuestionRequest dto
    ) {
        CreateQuestionResponse result = questionService.createQuestion(dto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_CREATE_SUCCESS, result));
    }

    @ApiOperation(value = "기본 제공 질문 전체 조회")
    @GetMapping("/common")
    public ResponseEntity<ResultResponse> getCommonQuestions(
            @RequestParam @Validated Integer page,
            @RequestParam @Validated Integer size
    ) {
        PageResponse result = questionService.getCommonQuestions(page, size);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_GET_SUCCESS, result));
    }

    @ApiOperation(value = "개인 질문 전체 조회")
    @GetMapping("/personal")
    public ResponseEntity<ResultResponse> getPersonalQuestions(
            @RequestParam @Validated Integer page,
            @RequestParam @Validated Integer size
    ) {
        PageResponse result = questionService.getPersonalQuestions(page, size);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_GET_SUCCESS, result));
    }

    @ApiOperation(value = "기본 제공 질문 중 선택한 분야의 랜덤 질문 제공")
    @GetMapping("/common/random")
    public ResponseEntity<ResultResponse> getCommonQuestion(
            @RequestParam Type type,
            @RequestParam int count
    ) {
        GetQuestionsResponse result = questionService.getCommonQuestion(type, count);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_GET_SUCCESS, result));
    }

    @ApiOperation(value = "질문 id를 받아 해당 질문 반환")
    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getCommonQuestion(
            @PathVariable @Validated Long id
    ) {
        QuestionDTO result = questionService.findQuestionById(id);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_GET_SUCCESS, result));
    }

    @ApiOperation(value = "질문 id를 받아 해당 질문 해답 반환")
    @GetMapping("/solution/{id}")
    public ResponseEntity<ResultResponse> getCommonQuestionSolution(
            @PathVariable @Validated Long id
    ) {
        QuestionResponseDTO result = questionService.getSolution(id);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_GET_SUCCESS, result));
    }

    @ApiOperation(value = "개인별 질문 수정")
    @PutMapping("/{id}")
    public ResponseEntity<ResultResponse> updatePersonalQuestion(
            @PathVariable @Validated Long id,
            @RequestBody @Validated UpdateQuestionRequest dto
    ) {
        UpdateQuestionResponse result = questionService.updateQuestion(id, dto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_UPDATE_SUCCESS, result));
    }

    @ApiOperation(value = "질문 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResultResponse> deletePersonalQuestion(
            @PathVariable @Validated Long id
    ) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_DELETE_SUCCESS));
    }
}
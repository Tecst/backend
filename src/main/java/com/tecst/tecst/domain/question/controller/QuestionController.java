package com.tecst.tecst.domain.question.controller;

import com.tecst.tecst.domain.question.dto.request.CreateQuestionRequest;
import com.tecst.tecst.domain.question.dto.request.UpdateQuestionRequest;
import com.tecst.tecst.domain.question.dto.response.*;
import com.tecst.tecst.domain.question.entity.Question;
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
    public ResponseEntity<ResultResponse> createPersonalQuestion(@RequestBody @Validated CreateQuestionRequest dto) {
        CreateQuestionResponse result = questionService.createQuestion(dto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_CREATE_SUCCESS, result));
    }

    @ApiOperation(value = "개인별 질문 전체 제공")
    @GetMapping
    public ResponseEntity<ResultResponse> getPersonalQuestion(
            @RequestParam @Validated Integer page,
            @RequestParam @Validated Integer size
    ) {
        PageResponse result = questionService.getPersonalQuestions(page, size);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_GET_SUCCESS, result));
    }

    // TODO 권한별 작업
    @ApiOperation(value = "기본 제공 질문 중 선택한 분야의 질문 제공")
    @GetMapping("/common")
    public GetQuestionResponse getCommonQuestion(@RequestParam Type type, @RequestParam int count) {
        return questionService.getCommonQuestion(type, count);
    }

    @ApiOperation(value = "질문 id를 받아 해당 질문 반환")
    @GetMapping("/{id}")
    public Question getCommonQuestion(@PathVariable Long id) {
        return questionService.findQuestionById(id);
    }

    @ApiOperation(value = "질문 id를 받아 해당 질문 해답 반환")
    @GetMapping("/solution/{id}")
    public GetCommonQuestionsSolution GetCommonQuestionSolution(@PathVariable Long id) {
        return questionService.getSolution(id);
    }

    @ApiOperation(value = "개인별 질문 수정")
    @PutMapping("/{id}")
    public ResponseEntity<ResultResponse> updatePersonalQuestion(@PathVariable Long id, @RequestBody UpdateQuestionRequest dto) {
        UpdateQuestionResponse result = questionService.updateQuestion(id, dto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_UPDATE_SUCCESS, result));
    }

    @ApiOperation(value = "질문 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResultResponse> deletePersonalQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_DELETE_SUCCESS));
    }
}
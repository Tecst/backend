package com.tecst.tecst.domain.personalquestion.controller;

import com.tecst.tecst.domain.personalquestion.dto.request.CreatePersonalQuestionRequest;
import com.tecst.tecst.domain.personalquestion.dto.response.CreatePersonalQuestionResponse;
import com.tecst.tecst.domain.personalquestion.service.PersonalQuestionService;
import com.tecst.tecst.global.result.ResultCode;
import com.tecst.tecst.global.result.ResultResponse;
import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "PersonalQuestion API")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/personal_question")
public class PersonalQuestionController {
    private final PersonalQuestionService personalQuestionService;

    @PostMapping("/new")
    public ResponseEntity<ResultResponse> createPersonalQuestion(CreatePersonalQuestionRequest dto) {
        CreatePersonalQuestionResponse result = personalQuestionService.createPersonalQuestion(dto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_CREATE_SUCCESS, result));
    }
}

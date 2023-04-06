package com.tecst.tecst.domain.personalquestion.controller;

import com.tecst.tecst.domain.personalquestion.dto.request.CreatePersonalQuestionRequest;
import com.tecst.tecst.domain.personalquestion.dto.request.UpdatePersonalQuestionRequest;
import com.tecst.tecst.domain.personalquestion.dto.response.CreatePersonalQuestionResponse;
import com.tecst.tecst.domain.personalquestion.dto.response.GetPersonalQuestionResponse;
import com.tecst.tecst.domain.personalquestion.dto.response.UpdatePersonalQuestionResponse;
import com.tecst.tecst.domain.personalquestion.service.PersonalQuestionService;
import com.tecst.tecst.global.result.ResultCode;
import com.tecst.tecst.global.result.ResultResponse;
import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = "PersonalQuestion API")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/personal_question")
public class PersonalQuestionController {
    private final PersonalQuestionService personalQuestionService;

    @PostMapping("/new")
    public ResponseEntity<ResultResponse> createPersonalQuestion(@RequestBody CreatePersonalQuestionRequest dto) {
        CreatePersonalQuestionResponse result = personalQuestionService.createPersonalQuestion(dto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_CREATE_SUCCESS, result));
    }

    @GetMapping
    public ResponseEntity<ResultResponse> getPersonalQuestion() {
        GetPersonalQuestionResponse result = personalQuestionService.getPersonalQuestion();
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_GET_SUCCESS, result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultResponse> updatePersonalQuestion(@PathVariable Long id,
                                                              @RequestBody UpdatePersonalQuestionRequest dto) {
        UpdatePersonalQuestionResponse result = personalQuestionService.updatePersonalQuestion(id, dto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_UPDATE_SUCCESS, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultResponse> deletePersonalQuestion(@PathVariable Long id) {
        personalQuestionService.deletePersonalQuestion(id);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.QUESTION_DELETE_SUCCESS));
    }
}

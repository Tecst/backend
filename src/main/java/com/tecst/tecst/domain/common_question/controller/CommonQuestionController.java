package com.tecst.tecst.domain.common_question.controller;

import com.tecst.tecst.domain.common_question.dto.response.CommonQuestionResponseDto;
import com.tecst.tecst.domain.common_question.dto.response.GetCommonQuestionsResponseDto;
import com.tecst.tecst.domain.common_question.repository.CommonQuestionRepository;
import com.tecst.tecst.domain.common_question.service.CommonQuestionService;
import com.tecst.tecst.domain.user.dto.request.CreateUserRequestDto;
import com.tecst.tecst.domain.user.mapper.UserMapper;
import com.tecst.tecst.domain.user.service.UserService;
import com.tecst.tecst.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tecst.tecst.global.result.ResultCode.USER_REGISTRATION_SUCCESS;

@Api(tags = "Common Question API")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/interview")
public class CommonQuestionController {
    private final CommonQuestionRepository commonQuestionRepository;
    private final UserMapper userMapper;
    private final CommonQuestionService commonQuestionService;

    @ApiOperation(value = "선택한 분야의 질문 제공")
    @GetMapping("/questions")
    public GetCommonQuestionsResponseDto GetCommonQuestion(@RequestParam String type,
                                                                           @RequestParam int count) {

        return commonQuestionService.GetQuestions(type, count);

    }
}
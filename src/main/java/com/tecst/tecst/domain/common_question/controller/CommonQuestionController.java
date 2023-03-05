package com.tecst.tecst.domain.common_question.controller;
import com.tecst.tecst.domain.common_question.dto.response.GetCommonQuestionsResponseDto;
import com.tecst.tecst.domain.common_question.dto.response.GetCommonQuestionsSolutionDto;
import com.tecst.tecst.domain.common_question.entity.CommonQuestion;
import com.tecst.tecst.domain.common_question.enumeration.Type;
import com.tecst.tecst.domain.common_question.service.CommonQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@Api(tags = "Common Question API")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/v1/interview")
public class CommonQuestionController {
    private final CommonQuestionService commonQuestionService;

    @ApiOperation(value = "선택한 분야의 질문 제공")
    @GetMapping("/questions")
    public GetCommonQuestionsResponseDto GetCommonQuestion(@RequestParam Type type,
                                                           @RequestParam int count) {

        return commonQuestionService.GetQuestions(type, count);
    }

    @ApiOperation(value = "질문 id를 입력하면 해당 질문 반환")
    @GetMapping("/questions/{id}")
    public CommonQuestion GetCommonQuestion(@PathVariable Long id) {
        return commonQuestionService.findCommonQuestionById(id);
    }

    @ApiOperation(value = "질문 해답 제공")
    @GetMapping("/solutions/{id}")
    public GetCommonQuestionsSolutionDto GetCommonQuestionSolution(@PathVariable Long id) {
        return commonQuestionService.GetSolutions(id);
    }
}
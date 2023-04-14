package com.tecst.tecst.domain.question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class GetQuestionResponse {
    private List<QuestionDTO> questionList;
}

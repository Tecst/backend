package com.tecst.tecst.domain.question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class GetQuestionSolutionResponse {
    private Long questionId;
    private String response;
}

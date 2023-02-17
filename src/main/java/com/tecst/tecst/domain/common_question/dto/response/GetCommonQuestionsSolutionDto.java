package com.tecst.tecst.domain.common_question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class GetCommonQuestionsSolutionDto {
    private Long commonQuestionId;
    private String response;

    public GetCommonQuestionsSolutionDto() {}
}

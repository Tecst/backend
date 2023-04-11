package com.tecst.tecst.domain.question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class GetCommonQuestionsSolution {
    private Long questionId;
    private String response;

    public GetCommonQuestionsSolution() {}
}

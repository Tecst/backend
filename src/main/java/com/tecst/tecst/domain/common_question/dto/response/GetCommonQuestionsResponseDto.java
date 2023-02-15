package com.tecst.tecst.domain.common_question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class GetCommonQuestionsResponseDto {
    private String type;
    private int count;
    private List<CommonQuestionResponseDto> questions_list;

    public GetCommonQuestionsResponseDto() {}
}

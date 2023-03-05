package com.tecst.tecst.domain.common_question.dto.response;

import com.tecst.tecst.domain.common_question.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class GetCommonQuestionsResponseDto {
    private Type type;
    private int count;
    private List<CommonQuestionResponseDto> questions_list;

    public GetCommonQuestionsResponseDto() {}
}

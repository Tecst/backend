package com.tecst.tecst.domain.question.dto.response;

import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.global.util.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class GetCommonQuestionsResponse {
    private Type type;
    private int count;
    private List<Question> questions_list;

    public GetCommonQuestionsResponse() {}
}

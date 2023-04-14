package com.tecst.tecst.domain.question.dto.response;

import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.global.util.Type;
import lombok.*;

@Builder
@Data
public class QuestionDTO {
    private Long questionId;
    private String content;
    private String response;
    private Type type;

    public static QuestionDTO listQuestionMapping(Question question){
        return QuestionDTO.builder()
                .questionId(question.getQuestionId())
                .content(question.getContent())
                .response(question.getResponse())
                .type(question.getType())
                .build();
    }
}

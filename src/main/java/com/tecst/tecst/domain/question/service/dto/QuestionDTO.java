package com.tecst.tecst.domain.question.service.dto;

import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.global.util.Type;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private Long questionId;
    private String content;
    private String response;
    private Type type;

    // TODO Mapper로 변경
    public static QuestionDTO questionMapping(Question question) {
        return QuestionDTO.builder()
                .questionId(question.getQuestionId())
                .content(question.getContent())
                .response(question.getResponse())
                .type(question.getType())
                .build();
    }
}

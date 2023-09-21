package com.tecst.tecst.domain.question.dto.response;

import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.global.util.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateQuestionResponse {
    private Long questionId;
    private Long userId;
    private String content;
    private String response;
    private Type type;

    public static CreateQuestionResponse from(Question question) {
        return new CreateQuestionResponse(
                question.getQuestionId(),
                question.getUser().getUserId(),
                question.getContent(),
                question.getResponse(),
                question.getType());
    }
}
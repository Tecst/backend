package com.tecst.tecst.domain.question.dto.response;

import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.global.util.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateQuestionResponse {
    private Long personalQuestionId;
    private Long userId;
    private String content;
    private String response;
    private Type type;

    public static UpdateQuestionResponse from(Question personalQuestion) {
        return new UpdateQuestionResponse(personalQuestion.getQuestionId(),
                personalQuestion.getUser().getUserId(),
                personalQuestion.getContent(),
                personalQuestion.getResponse(),
                personalQuestion.getType());
    }
}

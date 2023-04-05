package com.tecst.tecst.domain.personalquestion.dto.response;

import com.tecst.tecst.domain.common_question.enumeration.Type;
import com.tecst.tecst.domain.personalquestion.entity.PersonalQuestion;
import lombok.*;
@Getter
@AllArgsConstructor
public class CreatePersonalQuestionResponse {
    private Long personalQuestionId;
    private Long userId;
    private String content;
    private String response;
    private Type type;


    public static CreatePersonalQuestionResponse from(PersonalQuestion personalQuestion) {
        return new CreatePersonalQuestionResponse(personalQuestion.getPersonalQuestionId(),
                personalQuestion.getUser().getUserId(),
                personalQuestion.getContent(),
                personalQuestion.getResponse(),
                personalQuestion.getType());
    }
}
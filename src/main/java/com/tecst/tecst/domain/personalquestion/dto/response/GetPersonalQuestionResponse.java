package com.tecst.tecst.domain.personalquestion.dto.response;

import com.tecst.tecst.domain.common_question.enumeration.Type;
import com.tecst.tecst.domain.personalquestion.entity.PersonalQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPersonalQuestionResponse {
    private Long personalQuestionId;
    private Long userId;
    private String content;
    private String response;
    private Type type;

    public static GetPersonalQuestionResponse from(PersonalQuestion personalQuestion) {
        return new GetPersonalQuestionResponse(personalQuestion.getPersonalQuestionId(),
                personalQuestion.getUser().getUserId(),
                personalQuestion.getContent(),
                personalQuestion.getResponse(),
                personalQuestion.getType());
    }
}

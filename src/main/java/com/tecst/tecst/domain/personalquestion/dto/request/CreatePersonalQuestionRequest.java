package com.tecst.tecst.domain.personalquestion.dto.request;

import com.tecst.tecst.domain.common_question.enumeration.Type;
import com.tecst.tecst.domain.personalquestion.entity.PersonalQuestion;
import com.tecst.tecst.domain.user.entity.User;
import lombok.*;

@Getter
@AllArgsConstructor
public class CreatePersonalQuestionRequest {

    private Long userId;
    private String content;
    private String response;
    private Type type;


    public PersonalQuestion toEntity(User user) {
        return new PersonalQuestion(user, type, response, content);
    }
}

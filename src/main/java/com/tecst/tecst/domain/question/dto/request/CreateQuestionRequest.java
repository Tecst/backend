package com.tecst.tecst.domain.question.dto.request;

import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.global.util.Type;
import com.tecst.tecst.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateQuestionRequest {
    private String content;
    private String response;
    private Type type;

    public Question toEntity(User user) {
        return new Question(user, type, response, content);
    }
}

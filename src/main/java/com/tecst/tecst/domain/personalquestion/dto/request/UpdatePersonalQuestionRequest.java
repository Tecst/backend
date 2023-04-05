package com.tecst.tecst.domain.personalquestion.dto.request;

import com.tecst.tecst.domain.common_question.enumeration.Type;
import com.tecst.tecst.domain.personalquestion.entity.PersonalQuestion;
import com.tecst.tecst.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePersonalQuestionRequest {
    private String content;
    private String response;
    private Type type;
}

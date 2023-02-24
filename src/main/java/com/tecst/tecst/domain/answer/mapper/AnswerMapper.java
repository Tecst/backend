package com.tecst.tecst.domain.answer.mapper;

import com.tecst.tecst.domain.answer.dto.request.SaveAnswerRequestDto;
import com.tecst.tecst.domain.answer.entity.Answer;
import com.tecst.tecst.domain.common_question.entity.CommonQuestion;
import com.tecst.tecst.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {
    public Answer toEntity(SaveAnswerRequestDto dto, User user, CommonQuestion commonQuestion) {
        Answer answer = Answer.builder().response(dto.getResponse()).type(dto.getType())
                .user(user).commonQuestion(commonQuestion).build();
        return answer;
    }
}

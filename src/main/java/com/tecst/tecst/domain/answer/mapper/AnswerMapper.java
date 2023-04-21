package com.tecst.tecst.domain.answer.mapper;

import com.tecst.tecst.domain.answer.dto.request.SaveAnswerRequestDto;
import com.tecst.tecst.domain.answer.dto.response.GetAnswerResponseDto;
import com.tecst.tecst.domain.answer.entity.Answer;
import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {
    public Answer toEntity(SaveAnswerRequestDto dto, User user, Question commonQuestion) {
        Answer answer = Answer.builder()
                .answer(dto.getAnswer())
                .type(dto.getType())
                .user(user)
                .question(commonQuestion)
                .build();
        return answer;
    }

    public GetAnswerResponseDto toDto(Answer answer) {
        return GetAnswerResponseDto.builder()
                .userId(answer.getUser().getUserId())
                .type(answer.getType())
                .commonQuestionsId(answer.getQuestion().getQuestionId())
                .answer(answer.getAnswer())
                .answerId(answer.getAnswerId())
                .answerURL(answer.getAnswerURL())
                .build();
    }

    public Answer voiceAnswerToEntity(SaveAnswerRequestDto dto, User user, Question commonQuestion, String result) {
         return Answer.builder()
                 .answer(result)
                 .answerURL(dto.getAnswer())
                 .type(dto.getType())
                 .user(user)
                 .question(commonQuestion)
                 .build();
    }
}

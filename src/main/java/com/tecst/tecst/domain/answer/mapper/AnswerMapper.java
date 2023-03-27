package com.tecst.tecst.domain.answer.mapper;

import com.tecst.tecst.domain.answer.dto.request.SaveAnswerRequestDto;
import com.tecst.tecst.domain.answer.dto.request.SaveVoiceAnswerRequestDto;
import com.tecst.tecst.domain.answer.dto.response.GetAnswerResponseDto;
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

    public GetAnswerResponseDto toDto(Answer answer) {
        return GetAnswerResponseDto.builder().userId(answer.getUser().getUserId())
                .type(answer.getType()).commonQuestionsId(answer.getCommonQuestion().getCommonQuestionId())
                .response(answer.getResponse()).answerId(answer.getAnswerId()).build();
    }

    public Answer UploadsToEntity(SaveVoiceAnswerRequestDto dto, User user, CommonQuestion commonQuestion, String url) {
        Answer voiceAnswer = Answer.builder().response(url).type(dto.getType())
                .user(user).commonQuestion(commonQuestion).build();
        return voiceAnswer;
    }
}

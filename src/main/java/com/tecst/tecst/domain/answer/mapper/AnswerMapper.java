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
        Answer answer = Answer.builder().answer(dto.getAnswer()).type(dto.getType())
                .user(user).commonQuestion(commonQuestion).build();
        return answer;
    }

    public GetAnswerResponseDto toDto(Answer answer) {
        return GetAnswerResponseDto.builder()
                .userId(answer.getUser().getUserId())
                .type(answer.getType())
                .commonQuestionsId(answer.getCommonQuestion().getCommonQuestionId())
                .answer(answer.getAnswer())
                .answerId(answer.getId())
                .answerURL(answer.getAnswerURL()).build();
    }

    public Answer UploadsToEntity(SaveVoiceAnswerRequestDto dto, User user, CommonQuestion commonQuestion, String answerURL, String result) {
        Answer voiceAnswer = Answer.builder()
                .answer(result)
                .answerURL(answerURL)
                .type(dto.getType())
                .user(user)
                .commonQuestion(commonQuestion).build();
        return voiceAnswer;
    }
}

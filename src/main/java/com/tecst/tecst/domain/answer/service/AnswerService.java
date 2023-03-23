package com.tecst.tecst.domain.answer.service;

import com.tecst.tecst.domain.answer.ClovaSpeechClient;
import com.tecst.tecst.domain.answer.dto.request.SaveAnswerRequestDto;
import com.tecst.tecst.domain.answer.dto.response.GetAnswerResponseDto;
import com.tecst.tecst.domain.answer.dto.response.GetVoiceAnswerResponseDto;
import com.tecst.tecst.domain.answer.entity.Answer;
import com.tecst.tecst.domain.answer.exception.AnswerNotFound;
import com.tecst.tecst.domain.answer.mapper.AnswerMapper;
import com.tecst.tecst.domain.answer.repository.AnswerRepository;
import com.tecst.tecst.domain.common_question.entity.CommonQuestion;
import com.tecst.tecst.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final ClovaSpeechClient clovaSpeechClient;


    public void saveAnswer(SaveAnswerRequestDto dto, @Lazy User user, CommonQuestion commonquestion) {
        // Type이 voice면 STT 실행
        if (dto.getType().equals("voice")) {
            ClovaSpeechClient.NestRequestEntity requestEntity = new ClovaSpeechClient.NestRequestEntity();
            final String result = clovaSpeechClient.objectStorage(dto.getResponse(), requestEntity);
            dto.setResponse(result);
        }
        Answer answer = answerMapper.toEntity(dto, user, commonquestion);
        answerRepository.save(answer);
    }

    public GetVoiceAnswerResponseDto GetInterviewRecord(Long id) {
        Answer result = answerRepository.findById(id).orElseThrow(AnswerNotFound::new);
        GetVoiceAnswerResponseDto dto = new GetVoiceAnswerResponseDto();
        dto.setAnswerId(id);
        dto.setResponse(result.getResponse());
        return dto;
    }

    public GetAnswerResponseDto getAnswer(Long answersId) {
        Answer answer = answerRepository.findById(answersId).get();
        return answerMapper.toDto(answer);
    }
}

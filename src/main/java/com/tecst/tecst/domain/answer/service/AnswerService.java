package com.tecst.tecst.domain.answer.service;

import com.tecst.tecst.domain.answer.dto.request.SaveAnswerRequestDto;
import com.tecst.tecst.domain.answer.entity.Answer;
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

    public void saveAnswer(SaveAnswerRequestDto dto, @Lazy User user, CommonQuestion commonquestion) {
        Answer answer = answerMapper.toEntity(dto, user, commonquestion);
        answerRepository.save(answer);
    }
}

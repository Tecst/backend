package com.tecst.tecst.domain.common_question.service;

import com.tecst.tecst.domain.common_question.dto.response.CommonQuestionResponseDto;
import com.tecst.tecst.domain.common_question.dto.response.GetCommonQuestionsResponseDto;
import com.tecst.tecst.domain.common_question.entity.CommonQuestion;
import com.tecst.tecst.domain.common_question.repository.CommonQuestionRepository;
import com.tecst.tecst.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CommonQuestionService {
    private final CommonQuestionRepository commonQuestionRepository;

    public GetCommonQuestionsResponseDto GetQuestions(String type, int count) {
        List<CommonQuestionResponseDto> result = commonQuestionRepository.findCommonQuestionsByType(type, count);
        GetCommonQuestionsResponseDto dto = new GetCommonQuestionsResponseDto();
        dto.setType(type);
        dto.setCount(count);
        dto.setQuestions_list(result);
        return dto;
    }

    public CommonQuestion findCommonQuestionById(Long id) {
        CommonQuestion result = commonQuestionRepository.findByCommonQuestionId(id);
        return result;
    }
}

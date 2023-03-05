package com.tecst.tecst.domain.common_question.service;

import com.tecst.tecst.domain.common_question.dto.response.CommonQuestionResponseDto;
import com.tecst.tecst.domain.common_question.dto.response.GetCommonQuestionsResponseDto;
import com.tecst.tecst.domain.common_question.dto.response.GetCommonQuestionsSolutionDto;
import com.tecst.tecst.domain.common_question.entity.CommonQuestion;
import com.tecst.tecst.domain.common_question.enumeration.Type;
import com.tecst.tecst.domain.common_question.exception.QuestionNotFound;
import com.tecst.tecst.domain.common_question.exception.QuestionTypeNotFound;
import com.tecst.tecst.domain.common_question.repository.CommonQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CommonQuestionService {
    private final CommonQuestionRepository commonQuestionRepository;

    public GetCommonQuestionsResponseDto GetQuestions(Type type, int count) {

        try {
            Type.valueOf(String.valueOf(type));
        } catch (IllegalArgumentException e) {
            throw new QuestionTypeNotFound();
        }

        List<CommonQuestionResponseDto> result;
        if (type.name().equals("all")) result = commonQuestionRepository.findAllByCommonQuestionIdExists(count);
        else result = commonQuestionRepository.findCommonQuestionsByType(type, count);
        GetCommonQuestionsResponseDto dto = new GetCommonQuestionsResponseDto();
        dto.setType(type);
        dto.setCount(count);
        dto.setQuestions_list(result);
        return dto;
    }

    public CommonQuestion findCommonQuestionById(Long id) {
        return commonQuestionRepository.findByCommonQuestionId(id).orElseThrow(QuestionNotFound::new);
    }

    public GetCommonQuestionsSolutionDto GetSolutions(Long id) {
        CommonQuestion result = commonQuestionRepository.findByCommonQuestionId(id).orElseThrow(QuestionNotFound::new);
        GetCommonQuestionsSolutionDto dto = new GetCommonQuestionsSolutionDto();
        dto.setCommonQuestionId(id);
        dto.setResponse(result.getResponse());
        return dto;
    }
}

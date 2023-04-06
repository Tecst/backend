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
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CommonQuestionService {
    private final CommonQuestionRepository commonQuestionRepository;

    @PostConstruct
    public void initQuestions() throws IOException {
        ClassPathResource resource = new ClassPathResource("common_questions.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        String s;
        while ((s = br.readLine()) != null) {
            String[] tmp = s.split(";");
            CommonQuestion commonQuestion = CommonQuestion.builder().commonQuestionId(Long.valueOf(tmp[0]))
                    .contents(tmp[1])
                    .response(tmp[2])
                    .type(Type.valueOf(tmp[3])).build();
            commonQuestionRepository.save(commonQuestion);
        }
        br.close();
    }

    public GetCommonQuestionsResponseDto GetQuestions(Type type, int count) {

        try {
            Type.valueOf(String.valueOf(type));
        } catch (IllegalArgumentException e) {
            throw new QuestionTypeNotFound();
        }

        List<CommonQuestionResponseDto> result;
        if (type.name().equals("all")) result = commonQuestionRepository.findQuestions(count);
        else result = commonQuestionRepository.findQuestionsByType(type, count);
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

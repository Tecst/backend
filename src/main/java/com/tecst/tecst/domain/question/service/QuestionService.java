package com.tecst.tecst.domain.question.service;

import com.tecst.tecst.domain.question.dto.request.CreateQuestionRequest;
import com.tecst.tecst.domain.question.dto.request.UpdateQuestionRequest;
import com.tecst.tecst.domain.question.dto.response.*;
import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.domain.question.repository.QuestionCustomRepositoryImpl;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.domain.user.repository.UserRepository;
import com.tecst.tecst.domain.user.service.UserService;
import com.tecst.tecst.global.result.PageResponse;
import com.tecst.tecst.global.util.Type;
import com.tecst.tecst.domain.question.exception.QuestionNotFound;
import com.tecst.tecst.domain.question.exception.QuestionTypeNotFound;
import com.tecst.tecst.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionCustomRepositoryImpl questionCustomRepository;
    private final UserService userService;

    // TODO 리펙토링 필요
    public CreateQuestionResponse createQuestion(CreateQuestionRequest dto) {
        Question question = dto.toEntity(userService.getLoginUser());
        Question savedQuestion = questionRepository.save(question);
        return CreateQuestionResponse.from(savedQuestion);
    }

    public GetQuestionResponse getCommonQuestion(Type type, int count) {
        try {
            Type.valueOf(String.valueOf(type));
        } catch (IllegalArgumentException e) {
            throw new QuestionTypeNotFound();
        }

        List<Question> result;
        if (type.name().equals("all")) result = questionCustomRepository.findQuestions(count);
        else result = questionCustomRepository.findQuestionsByType(type, count);
        return new GetQuestionResponse(result.stream()
                .map(QuestionDTO::listQuestionMapping)
                .collect(Collectors.toList()));
    }

    public PageResponse getPersonalQuestion(Integer page, Integer size) {
        Page<Question> questionList = questionRepository.findAllByUser(userService.getLoginUser(), PageRequest.of(page, size));
        return PageResponse.pageResponseMapping(questionList);
    }

    public UpdateQuestionResponse updateQuestion(Long id, UpdateQuestionRequest dto) {
        Question target = questionRepository.findById(id).orElseThrow(QuestionNotFound::new);
        target.update(dto.getContent(), dto.getResponse(), dto.getType());
        return UpdateQuestionResponse.from(target);
    }

    public void deleteQuestion(Long id) {
        Question target = questionRepository.findById(id).orElseThrow(QuestionNotFound::new);
        questionRepository.delete(target);
    }


    public Question findQuestionById(Long id) {
        return questionRepository.findByQuestionId(id).orElseThrow(QuestionNotFound::new);
    }

    public GetCommonQuestionsSolution getSolution(Long id) {
        com.tecst.tecst.domain.question.entity.Question result = questionRepository.findByQuestionId(id).orElseThrow(QuestionNotFound::new);
        GetCommonQuestionsSolution dto = new GetCommonQuestionsSolution();
        dto.setQuestionId(id);
        dto.setResponse(result.getResponse());
        return dto;
    }
}

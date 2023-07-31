package com.tecst.tecst.domain.question.service;

import com.tecst.tecst.domain.question.dto.request.CreateQuestionRequest;
import com.tecst.tecst.domain.question.dto.request.UpdateQuestionRequest;
import com.tecst.tecst.domain.question.dto.response.*;
import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.domain.question.repository.QuestionCustomRepositoryImpl;
import com.tecst.tecst.domain.question.service.dto.QuestionDTO;
import com.tecst.tecst.domain.question.service.dto.QuestionResponseDTO;
import com.tecst.tecst.domain.user.service.UserService;
import com.tecst.tecst.global.result.PageResponse;
import com.tecst.tecst.global.util.Type;
import com.tecst.tecst.domain.question.exception.QuestionNotFound;
import com.tecst.tecst.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public CreateQuestionResponse createQuestion(CreateQuestionRequest dto) {
        return CreateQuestionResponse.from(
                questionRepository.save(
                        dto.toEntity(userService.getLoginUser())
                )
        );
    }

    public GetQuestionsResponse getCommonQuestion(Type type, int count) {
        List<Question> result;
        if (type.name().equals("all"))
            result = questionCustomRepository.findQuestions(count);
        else
            result = questionCustomRepository.findQuestionsByType(type, count);
        return new GetQuestionsResponse(result.stream()
                .map(QuestionDTO::QuestionMapping)
                .collect(Collectors.toList()));
    }

    public PageResponse getCommonQuestions(Integer page, Integer size) {
        Page<Question> questionList = questionRepository.findAllByUser_role("ADMIN", PageRequest.of(page, size));
        return PageResponse.pageResponseMapping(questionList);
    }

    public PageResponse getPersonalQuestions(Integer page, Integer size) {
        Page<Question> questionList = questionRepository.findAllByUser(userService.getLoginUser(), PageRequest.of(page, size));
        return PageResponse.pageResponseMapping(questionList);
    }

    public UpdateQuestionResponse updateQuestion(Long id, UpdateQuestionRequest dto) {
        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFound::new);
        question.update(dto);
        questionRepository.save(question);
        return UpdateQuestionResponse.from(question);
    }

    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFound::new);
        questionRepository.delete(question);
    }

    public QuestionDTO findQuestionById(Long id) {
        Question question = questionRepository.findByQuestionId(id).orElseThrow(QuestionNotFound::new);
        return QuestionDTO.QuestionMapping(question);
    }

    public QuestionResponseDTO getSolution(Long id) {
        Question question = questionRepository.findByQuestionId(id).orElseThrow(QuestionNotFound::new);
        return QuestionResponseDTO.QuestionResponseMapping(question);
    }
}

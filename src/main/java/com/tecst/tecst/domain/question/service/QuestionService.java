package com.tecst.tecst.domain.question.service;

import com.tecst.tecst.domain.question.dto.request.CreateQuestionRequest;
import com.tecst.tecst.domain.question.dto.request.UpdateQuestionRequest;
import com.tecst.tecst.domain.question.dto.response.*;
import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.domain.user.service.UserService;
import com.tecst.tecst.global.util.Type;
import com.tecst.tecst.domain.question.exception.QuestionNotFound;
import com.tecst.tecst.domain.question.exception.QuestionTypeNotFound;
import com.tecst.tecst.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
    private final UserService userService;

    // TODO 기본 유저 정보 property source 설정
    // DB 테이블에 질문 및 기본 유저 저장
    // 첫 빌드 후, 주석 처리 요망
//    @PostConstruct
//    public void initQuestions() throws IOException {
//        ClassPathResource resource = new ClassPathResource("common_questions.txt");
//        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
//        String s;
//
//        User adminUser = userRepository.save(User.builder()
//                                            .email("admin@gmail.com")
//                                            .password("admin")
//                                            .role("ADMIN")
//                                            .build());
//
//        User user = userRepository.save(User.builder()
//                                        .email("user@gmail.com")
//                                        .password("user")
//                                        .role("USER")
//                                        .build());
//
//        while ((s = br.readLine()) != null) {
//            String[] tmp = s.split(";");
//            Question commonQuestion = Question.builder()
//                    .questionId(Long.valueOf(tmp[0]))
//                    .content(tmp[1])
//                    .response(tmp[2])
//                    .type(Type.valueOf(tmp[3]))
//                    .isDelete(Boolean.FALSE)
//                    .user(adminUser)
//                    .build();
//
//            questionRepository.save(commonQuestion);
//        }
//        br.close();
//    }

    // TODO 리펙토링 필요
    @Transactional
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
        if (type.name().equals("all")) result = questionRepository.findQuestions(count);
        else result = questionRepository.findQuestionsByType(type, count);
        return new GetQuestionResponse(result.stream()
                .map(QuestionDTO::listQuestionMapping)
                .collect(Collectors.toList()));
    }

    public GetQuestionResponse getPersonalQuestion() {
        List<Question> questionList = questionRepository.findAllByUser(userService.getLoginUser());
        return new GetQuestionResponse(questionList.stream()
                .map(QuestionDTO::listQuestionMapping)
                .collect(Collectors.toList()));
    }

    @Transactional
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

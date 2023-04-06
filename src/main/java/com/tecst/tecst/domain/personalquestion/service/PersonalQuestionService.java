package com.tecst.tecst.domain.personalquestion.service;

import com.tecst.tecst.domain.common_question.exception.QuestionNotFound;
import com.tecst.tecst.domain.personalquestion.dto.request.CreatePersonalQuestionRequest;
import com.tecst.tecst.domain.personalquestion.dto.request.UpdatePersonalQuestionRequest;
import com.tecst.tecst.domain.personalquestion.dto.response.CreatePersonalQuestionResponse;
import com.tecst.tecst.domain.personalquestion.dto.response.GetPersonalQuestionResponse;
import com.tecst.tecst.domain.personalquestion.dto.response.UpdatePersonalQuestionResponse;
import com.tecst.tecst.domain.personalquestion.entity.PersonalQuestion;
import com.tecst.tecst.domain.personalquestion.repository.PersonalQuestionRepository;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.domain.user.repository.UserRepository;
import com.tecst.tecst.global.error.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Log4j2
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalQuestionService {
    private final PersonalQuestionRepository personalQuestionRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreatePersonalQuestionResponse createPersonalQuestion(CreatePersonalQuestionRequest dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userRepository.findByEmail(username).get();
        PersonalQuestion personalQuestion = dto.toEntity(user);
        PersonalQuestion savedQuestion = personalQuestionRepository.save(personalQuestion);
        return CreatePersonalQuestionResponse.from(savedQuestion);
    }

    public GetPersonalQuestionResponse getPersonalQuestion() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        Long userId = userRepository.findByEmail(username).get().getUserId();
        PersonalQuestion personalQuestion = personalQuestionRepository.findById(userId).orElseThrow(QuestionNotFound::new);
        return GetPersonalQuestionResponse.from(personalQuestion);
    }

    @Transactional
    public UpdatePersonalQuestionResponse updatePersonalQuestion(Long id, UpdatePersonalQuestionRequest dto) {
        PersonalQuestion target = personalQuestionRepository.findById(id).orElseThrow(QuestionNotFound::new);
        target.update(dto.getContent(), dto.getResponse(), dto.getType());
        return UpdatePersonalQuestionResponse.from(target);
    }

    public void deletePersonalQuestion(Long id) {
        PersonalQuestion target = personalQuestionRepository.findById(id).orElseThrow(QuestionNotFound::new);
        personalQuestionRepository.delete(target);
    }
}



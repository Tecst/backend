package com.tecst.tecst.domain.personalquestion.service;

import com.tecst.tecst.domain.personalquestion.dto.request.CreatePersonalQuestionRequest;
import com.tecst.tecst.domain.personalquestion.dto.response.CreatePersonalQuestionResponse;
import com.tecst.tecst.domain.personalquestion.entity.PersonalQuestion;
import com.tecst.tecst.domain.personalquestion.repository.PersonalQuestionRepository;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.domain.user.repository.UserRepository;
import com.tecst.tecst.global.error.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    public CreatePersonalQuestionResponse createPersonalQuestion(CreatePersonalQuestionRequest dto) {
        User user = userRepository.findById(dto.getUserId()).get();
        PersonalQuestion personalQuestion = dto.toEntity(user);
        PersonalQuestion savedQuestion = personalQuestionRepository.save(personalQuestion);
        return CreatePersonalQuestionResponse.from(savedQuestion);
    }
}



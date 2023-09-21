package com.tecst.tecst.global.util;

import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.domain.question.repository.QuestionRepository;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class InitClass {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    // TODO 기본 유저 정보 property source 설정
//     DB 테이블에 질문 및 기본 유저 저장
//     첫 빌드 후, 주석 처리 요망
//    @PostConstruct
//    public void initQuestionUser() throws IOException {
//        User adminUser = userRepository.save(User.builder()
//                .email("admin@gmail.com")
//                .password("admin")
//                .role("ADMIN")
//                .build());
//
//        User user = userRepository.save(User.builder()
//                .email("user@gmail.com")
//                .password("user")
//                .role("USER")
//                .build());
//
//        ClassPathResource resource = new ClassPathResource("common_questions.txt");
//        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
//        String s;
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
}

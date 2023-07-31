package com.tecst.tecst.domain.question.repository;

import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByQuestionId(Long id);

    Page<Question> findAllByUser(User user, Pageable pageable);
    Page<Question> findAllByUser_role(String role, Pageable pageable);


}

package com.tecst.tecst.domain.answer.repository;

import com.tecst.tecst.domain.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerRepository extends JpaRepository<Answer, Long> {
}

package com.tecst.tecst.domain.answer.repository;

import com.tecst.tecst.domain.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    Optional<Answer> findByAnswerId(UUID id);
}

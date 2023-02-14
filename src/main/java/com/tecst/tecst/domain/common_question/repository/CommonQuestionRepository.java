package com.tecst.tecst.domain.common_question.repository;

import com.tecst.tecst.domain.common_question.entity.CommonQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommonQuestionRepository extends JpaRepository<CommonQuestion, UUID> {
}

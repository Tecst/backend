package com.tecst.tecst.domain.question.repository;

import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.global.util.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface QuestionCustomRepository {
    List<Question> findQuestionsByType(Type type, int count);
    List<Question> findQuestions(int count);
}

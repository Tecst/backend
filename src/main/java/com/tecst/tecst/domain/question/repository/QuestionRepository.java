package com.tecst.tecst.domain.question.repository;

import com.tecst.tecst.domain.question.dto.response.CommonQuestionResponse;
import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.global.util.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "select distinct * from question where type = :#{#type.name()} order by rand() limit :count", nativeQuery = true)
    List<CommonQuestionResponse> findQuestionsByType(Type type, int count);

    @Query(value = "select * from question order by rand() limit :count", nativeQuery = true)
    List<CommonQuestionResponse> findQuestions(int count);

    Optional<Question> findByQuestionId(Long id);

    List<Question> findAllByUser(User user);

}

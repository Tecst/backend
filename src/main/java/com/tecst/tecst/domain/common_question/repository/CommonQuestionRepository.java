package com.tecst.tecst.domain.common_question.repository;

import com.tecst.tecst.domain.common_question.dto.response.CommonQuestionResponseDto;
import com.tecst.tecst.domain.common_question.entity.CommonQuestion;
import com.tecst.tecst.domain.common_question.enumeration.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommonQuestionRepository extends JpaRepository<CommonQuestion, Long> {

    @Query(value = "select distinct * from common_question where type = :#{#type.name()} order by rand() limit :count", nativeQuery = true)
    List<CommonQuestionResponseDto> findQuestionsByType(Type type, int count);

    @Query(value = "select * from common_question order by rand() limit :count", nativeQuery = true)
    List<CommonQuestionResponseDto>findQuestions(int count);


    Optional<CommonQuestion> findByCommonQuestionId(Long id);
}

package com.tecst.tecst.domain.common_question.repository;

import com.tecst.tecst.domain.common_question.dto.response.CommonQuestionResponseDto;
import com.tecst.tecst.domain.common_question.entity.CommonQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommonQuestionRepository extends JpaRepository<CommonQuestion, UUID> {

    @Query(value = "select distinct * from common_question where type = :type order by rand() limit :count", nativeQuery = true)
    List<CommonQuestionResponseDto> findCommonQuestionsByType(String type, int count);

    @Query(value = "select * from common_question order by rand() limit :count", nativeQuery = true)
    List<CommonQuestionResponseDto> findAllByCommonQuestionIdExists(int count);


    CommonQuestion findByCommonQuestionId(Long id);
}

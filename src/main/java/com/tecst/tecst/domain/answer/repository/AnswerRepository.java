package com.tecst.tecst.domain.answer.repository;

import com.tecst.tecst.domain.answer.dto.response.AverageResponseDto;
import com.tecst.tecst.domain.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(value = "select Q.type as type, " +
            "(select ifnull(round(avg(A.score), 2), 0) as avg from answer A " +
            "RIGHT OUTER join question QU ON(A.question_id=QU.question_id AND A.user_id = :userId) " +
            "where QU.type = Q.type group by QU.type) as userAvg, " +
            "ifnull(round(avg(score), 2), 0) as allAvg " +
            "from answer A RIGHT OUTER join question Q ON(A.question_id=Q.question_id) " +
            "group by Q.type", nativeQuery = true)
    List<AverageResponseDto> findAvg(@Param("userId") Long userId);
}

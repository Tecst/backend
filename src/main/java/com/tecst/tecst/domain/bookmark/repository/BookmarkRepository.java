package com.tecst.tecst.domain.bookmark.repository;

import com.tecst.tecst.domain.bookmark.dto.response.BookmarkResponseDto;
import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByBookmarkId(Long id);

    Bookmark findByUser_UserIdAndCommonQuestion_CommonQuestionId(Long userId, Long commonQuestionId);

    @Query(value = "select b.bookmark_id, cq.*, a.answer, a.answerURL, a.type as answerType from bookmark b" +
            " INNER JOIN common_question cq ON b.common_question_id = cq.common_question_id" +
            " INNER JOIN (" +
            " SELECT common_question_id, answer, answerURL, type FROM answer " +
            " WHERE user_id = :userId) a " +
            " ON b.common_question_id = a.common_question_id" +
            " WHERE b.user_id = :userId", nativeQuery = true)
    List<BookmarkResponseDto> findBookmarksByUser(@Param("userId") Long userId);
}

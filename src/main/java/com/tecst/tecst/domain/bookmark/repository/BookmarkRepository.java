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

    Bookmark findByUser_UserIdAndQuestion_QuestionId(Long userId, Long questionId);

//    @Query(value = "select b.bookmark_id, cq.*, a.answer, a.answerURL, a.type as answerType from bookmark b" +
//            " INNER JOIN question cq ON b.question_id = cq.question_id" +
//            " INNER JOIN (" +
//            " SELECT question_id, answer, answerURL, type FROM answer " +
//            " WHERE user_id = :userId) a " +
//            " ON b.question_id = a.question_id" +
//            " WHERE b.user_id = :userId", nativeQuery = true)
    List<Bookmark> findByUserUserId(Long user_id);
}

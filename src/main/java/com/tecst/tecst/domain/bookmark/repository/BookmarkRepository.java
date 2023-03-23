package com.tecst.tecst.domain.bookmark.repository;

import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByBookmarkId(Long id);

    Bookmark findByUser_UserIdAndCommonQuestion_CommonQuestionId(Long userId, Long commonQuestionId);
}

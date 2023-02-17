package com.tecst.tecst.domain.bookmark.repository;

import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookmarkRepository extends JpaRepository<Bookmark, UUID> {

    Optional<Bookmark> findByBookmarkId(UUID id);
}

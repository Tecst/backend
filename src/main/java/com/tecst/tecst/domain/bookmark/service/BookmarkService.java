package com.tecst.tecst.domain.bookmark.service;

import com.tecst.tecst.domain.bookmark.dto.response.DeleteBookmarkResponseDto;
import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import com.tecst.tecst.domain.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public DeleteBookmarkResponseDto DeleteBookmark(UUID bookmarkId) {
        Bookmark result = bookmarkRepository.findByBookmarkId(bookmarkId).orElseThrow(null);
        DeleteBookmarkResponseDto dto = new DeleteBookmarkResponseDto();
        dto.setBookmark_id(bookmarkId);
        dto.setUser_id(result.getUser().getUserId());
        dto.setCommon_questions_id(result.getCommonQuestion().getCommonQuestionId());
        return dto;
    }
}

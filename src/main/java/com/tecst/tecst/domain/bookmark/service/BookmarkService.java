package com.tecst.tecst.domain.bookmark.service;

import com.tecst.tecst.domain.bookmark.dto.request.RegistBookmarkRequestDto;
import com.tecst.tecst.domain.bookmark.dto.response.DeleteBookmarkResponseDto;
import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import com.tecst.tecst.domain.bookmark.exception.BookmarkDuplicated;
import com.tecst.tecst.domain.bookmark.mapper.BookmarkMapper;

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
    private final BookmarkMapper bookmarkMapper;

    public void register(RegistBookmarkRequestDto dto) {
        if(!(bookmarkRepository.findByUser_UserIdAndCommonQuestion_CommonQuestionId(dto.getUserId(), dto.getCommonQuestionId())==null))
            throw new BookmarkDuplicated();
        Bookmark bookmark = bookmarkMapper.toEntity(dto);
        bookmarkRepository.save(bookmark);
    }
        
    public DeleteBookmarkResponseDto DeleteBookmark(Long bookmarkId) {
        Bookmark result = bookmarkRepository.findById(bookmarkId).orElseThrow(null);
        bookmarkRepository.deleteById(bookmarkId);
        DeleteBookmarkResponseDto dto = new DeleteBookmarkResponseDto();
        dto.setBookmark_id(bookmarkId);
        dto.setUser_id(result.getUser().getUserId());
        dto.setCommon_questions_id(result.getCommonQuestion().getCommonQuestionId());
        return dto;
    }
}

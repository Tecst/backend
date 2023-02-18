package com.tecst.tecst.domain.bookmark.service;

import com.tecst.tecst.domain.bookmark.dto.request.RegistBookmarkRequestDto;
import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import com.tecst.tecst.domain.bookmark.mapper.BookmarkMapper;
import com.tecst.tecst.domain.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;

    public void register(RegistBookmarkRequestDto dto) {
        Bookmark bookmark = bookmarkMapper.toEntity(dto);
        bookmarkRepository.save(bookmark);
    }
}

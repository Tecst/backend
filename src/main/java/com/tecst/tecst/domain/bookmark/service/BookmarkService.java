package com.tecst.tecst.domain.bookmark.service;

import com.tecst.tecst.domain.auth.service.CustomUserDetailsService;
import com.tecst.tecst.domain.bookmark.dto.request.RegistBookmarkRequestDto;
import com.tecst.tecst.domain.bookmark.dto.response.BookmarkCreateResponse;
import com.tecst.tecst.domain.bookmark.dto.response.BookmarkResponseDto;
import com.tecst.tecst.domain.bookmark.dto.response.BookmarkDeleteResoponse;
import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import com.tecst.tecst.domain.bookmark.exception.BookmarkDuplicated;
import com.tecst.tecst.domain.bookmark.exception.BookmarkNotFound;
import com.tecst.tecst.domain.bookmark.mapper.BookmarkMapper;

import com.tecst.tecst.domain.bookmark.repository.BookmarkRepository;
import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.domain.question.exception.QuestionNotFound;
import com.tecst.tecst.domain.question.repository.QuestionRepository;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final QuestionRepository questionRepository;
    private final BookmarkMapper bookmarkMapper;
    private final CustomUserDetailsService userService;
    private final UserService userService2;

    public BookmarkCreateResponse register(RegistBookmarkRequestDto dto) {
        User user = userService2.getLoginUser();
        Question question = questionRepository.findById(dto.getQuestionId()).orElseThrow(
                () -> new QuestionNotFound());

        if(!(bookmarkRepository.findByUser_UserIdAndQuestion_QuestionId(user.getUserId(),
                dto.getQuestionId())==null))
            throw new BookmarkDuplicated();

        Bookmark bookmark = bookmarkMapper.toEntity(question, user);
        bookmarkRepository.save(bookmark);
        return new BookmarkCreateResponse(bookmark.getBookmarkId());

    }
        
    public BookmarkDeleteResoponse DeleteBookmark(Long bookmarkId) {
        Bookmark result = bookmarkRepository.findById(bookmarkId).orElseThrow(BookmarkNotFound::new);
        bookmarkRepository.deleteById(bookmarkId);
        BookmarkDeleteResoponse dto = new BookmarkDeleteResoponse();
        dto.setBookmark_id(bookmarkId);
        dto.setUser_id(result.getUser().getUserId());
        dto.setCommon_questions_id(result.getQuestion().getQuestionId());
        return dto;
    }

    public List<BookmarkResponseDto> GetBookmarks() {
        Long userId = userService2.getLoginUser().getUserId();
        List<BookmarkResponseDto> list = bookmarkRepository.findByUserUserId(userId)
                .stream()
                .map(BookmarkResponseDto::toBookmarkResponseDto)
                .collect(Collectors.toList());

        return list;
    }
}

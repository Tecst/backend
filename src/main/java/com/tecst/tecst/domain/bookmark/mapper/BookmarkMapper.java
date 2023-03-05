package com.tecst.tecst.domain.bookmark.mapper;

import com.tecst.tecst.domain.bookmark.dto.request.RegistBookmarkRequestDto;
import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import com.tecst.tecst.domain.bookmark.repository.BookmarkRepository;
import com.tecst.tecst.domain.common_question.exception.QuestionNotFound;
import com.tecst.tecst.domain.common_question.repository.CommonQuestionRepository;
import com.tecst.tecst.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookmarkMapper {
    private final UserRepository userRepository;
    private final CommonQuestionRepository commonQuestionRepository;

    public RegistBookmarkRequestDto toDto(Bookmark bookmark) {
        return RegistBookmarkRequestDto.builder().build().builder().userId(bookmark.getUser().getUserId())
                .commonQuestionId(bookmark.getCommonQuestion().getCommonQuestionId()).build();
    }

    public Bookmark toEntity(RegistBookmarkRequestDto dto) {
        Bookmark bookmark = Bookmark.builder().user(userRepository.findByUserId(dto.getUserId()))
                .commonQuestion(commonQuestionRepository.findByCommonQuestionId(dto.getCommonQuestionId()).orElseThrow(QuestionNotFound::new)).build();
        return bookmark;
    }
}

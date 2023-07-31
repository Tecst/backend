package com.tecst.tecst.global.result;

import com.tecst.tecst.domain.question.dto.response.QuestionDTO;
import com.tecst.tecst.domain.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse {

    private List<QuestionDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public static PageResponse pageResponseMapping(Page<Question> question){

        return PageResponse.builder()
                .content(question.getContent().stream()
                        .map(QuestionDTO::listQuestionMapping)
                        .collect(Collectors.toList()))
                .pageSize(question.getSize())
                .pageNo(question.getNumber())
                .totalElements(question.getTotalElements())
                .totalPages(question.getTotalPages())
                .last(question.isLast())
                .build();
    }
}
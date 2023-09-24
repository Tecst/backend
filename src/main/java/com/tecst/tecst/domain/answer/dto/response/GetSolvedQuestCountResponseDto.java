package com.tecst.tecst.domain.answer.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class GetSolvedQuestCountResponseDto {
    private Long userId;
    private List<SolvedQuestCountResponseDto> solvedQuestCountList;
    public GetSolvedQuestCountResponseDto(Long userId, List<SolvedQuestCountResponseDto> solvedQuestCountList ){
        this.userId = userId;
        this.solvedQuestCountList = solvedQuestCountList;
    }
}

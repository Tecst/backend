package com.tecst.tecst.domain.answer.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class GetAverageResponseDto {
    private Long userId;
    private List<AverageResponseDto> avgList;
    public GetAverageResponseDto(Long userId, List<AverageResponseDto> avgList){
        this.userId = userId;
        this.avgList = avgList;
    }
}

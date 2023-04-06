package com.tecst.tecst.domain.common_question.entity;

import com.tecst.tecst.domain.common_question.enumeration.Type;
import lombok.*;

import javax.persistence.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CommonQuestion {
    @Id
    @GeneratedValue
    private Long commonQuestionId;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "Response", length = 500)
    private String response;

    @Column(name = "Contents", length = 100)
    private String contents;
}
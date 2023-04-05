package com.tecst.tecst.domain.personalquestion.entity;

import com.tecst.tecst.domain.common_question.enumeration.Type;
import com.tecst.tecst.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PersonalQuestion {
    @Id
    @GeneratedValue
    private Long personalQuestionId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "response")
    private String response;

    @Column(name = "content")
    private String content;
}
package com.tecst.tecst.domain.personalquestion.entity;

import com.tecst.tecst.domain.common_question.enumeration.Type;
import com.tecst.tecst.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE personal_question SET is_deleted = true WHERE personal_question_id = ?")
@Where(clause = "is_deleted = false")
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

    @Builder
    public PersonalQuestion(User user, Type type, String response, String content) {
        this.user = user;
        this.type = type;
        this.response = response;
        this.content = content;
    }

    public void update(String content,
                       String response,
                       Type type) {

        this.content = content;
        this.response = response;
        this.type = type;
    }
}
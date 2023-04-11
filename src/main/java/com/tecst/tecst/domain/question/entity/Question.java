package com.tecst.tecst.domain.question.entity;

import com.tecst.tecst.global.util.Type;
import com.tecst.tecst.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SQLDelete(sql = "UPDATE question SET is_deleted = true WHERE question_id = ?")
@Where(clause = "is_deleted = false")
public class Question {
    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "type", length = 100)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "response", length = 500)
    private String response;

    @Column(name = "content", length = 100)
    private String content;

    @Column(name = "is_deleted")
    private Boolean isDelete = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Question(User user, Type type, String response, String content) {
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
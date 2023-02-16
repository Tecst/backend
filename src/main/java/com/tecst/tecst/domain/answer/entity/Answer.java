package com.tecst.tecst.domain.answer.entity;

import com.tecst.tecst.domain.Type;
import com.tecst.tecst.domain.common_question.entity.CommonQuestion;
import com.tecst.tecst.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.FetchType.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Answer {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "BINARY(16)")
    private UUID answerId;

    @Column(name = "Type", length = 20)
    private String type;

    @Column(name = "Response", length = 100)
    private String response;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "commonQuestionId")
    private CommonQuestion commonQuestion;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    private Answer(User user, String type, CommonQuestion commonQuestion, String response) {
        this.user = user;
        this.type = type;
        this.commonQuestion = commonQuestion;
        this.response = response;
    }

}

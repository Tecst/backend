package com.tecst.tecst.domain.answer.entity;

import com.tecst.tecst.domain.common_question.entity.CommonQuestion;
import com.tecst.tecst.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "Type", length = 20)
    private String type;

    @Column(name = "answer", length = 100)
    private String answer;

    @Column(name = "answerURL", length = 100)
    private String answerURL;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "commonQuestionId")
    private CommonQuestion commonQuestion;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

}

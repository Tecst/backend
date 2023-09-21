package com.tecst.tecst.domain.answer.entity;

import com.tecst.tecst.domain.question.entity.Question;
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
    private String type; // 텍스트 or 음성

    @Column(name = "answer", length = 100)
    private String answer; // STT 결과

    @Column(name = "answer_url", length = 100)
    private String answerURL; // URL

    @Column(name = "score")
    private int score;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private String feedBack; // GPT 평가

    public void update(int score) {
        this.score = score;
    }
}

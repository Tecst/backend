package com.tecst.tecst.domain.common_question.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tecst.tecst.domain.Type;
import com.tecst.tecst.domain.answer.entity.Answer;
import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import com.tecst.tecst.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommonQuestion {
    @Id
    @GeneratedValue
    private Long commonQuestionId;

//    @Enumerated(EnumType.STRING)
    @Column(name = "Type", length = 30)
    private String type;

    @Column(name = "Response", length = 500)
    private String response;

    @Column(name = "Contents", length = 100)
    private String contents;

    @OneToMany(mappedBy = "commonQuestion", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<Bookmark>();

    @JsonIgnore
    @OneToMany(mappedBy = "commonQuestion", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<Answer>();

    @Builder
    private CommonQuestion(String type, String response, String contents) {
        this.type = type;
        this.response = response;
        this.contents = contents;
    }
}
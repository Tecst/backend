package com.tecst.tecst.domain.user.entity;

import com.tecst.tecst.domain.answer.entity.Answer;
import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(name = "Email", length = 30)
    private String email;

    @Column(name = "Password", length = 10)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookmarks = new ArrayList<Bookmark>();

    @OneToMany(mappedBy = "user")
    private List<Answer> answers = new ArrayList<Answer>();

//    @OneToMany(mappedBy = "User")
//    private List<Personal_Question> personal_questions = new ArrayList<Personal_Question>();
}

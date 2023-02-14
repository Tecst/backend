package com.tecst.tecst.domain.common_question.entity;

import com.tecst.tecst.domain.Type;
import com.tecst.tecst.domain.bookmark.entity.Bookmark;
import com.tecst.tecst.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "BINARY(16)")
    private UUID commonQuestionId;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "Response", length = 100)
    private String response;

    @Column(name = "Contents", length = 100)
    private String contents;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "commonQuestion")
    private List<Bookmark> bookmarks = new ArrayList<Bookmark>();
}
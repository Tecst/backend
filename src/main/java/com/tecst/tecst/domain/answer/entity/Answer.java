package com.tecst.tecst.domain.answer.entity;

import com.tecst.tecst.domain.Type;
import com.tecst.tecst.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "Response", length = 100)
    private String response;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

}

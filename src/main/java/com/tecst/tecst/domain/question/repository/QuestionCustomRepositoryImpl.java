package com.tecst.tecst.domain.question.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tecst.tecst.domain.question.entity.Question;
import com.tecst.tecst.global.util.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tecst.tecst.domain.question.entity.QQuestion.question;
import static com.tecst.tecst.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class QuestionCustomRepositoryImpl implements QuestionCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Question> findQuestionsByType(Type type, int count) {
        return jpaQueryFactory
                .selectFrom(question)
                .join(question.user, user)
                .where(question.type.eq(type)
                        .and(user.userId.eq(1L)))
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(count)
                .fetch();
    }

    @Override
    public List<Question> findQuestions(int count) {
        return jpaQueryFactory
                .selectFrom(question)
                .join(question.user, user)
                .where(user.userId.eq(1L))
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(count)
                .fetch();
    }
}

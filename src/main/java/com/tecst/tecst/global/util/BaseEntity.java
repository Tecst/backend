package com.tecst.tecst.global.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@MappedSuperclass
@Where(clause = "deleted_at is null")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(updatable = false, name = "created_at", columnDefinition = "datetime null default null")
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column(name = "updated_at", columnDefinition = "datetime null default null")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "datetime null default null")
    private LocalDateTime deletedAt;

    public boolean isDeleted() {
        return null != this.deletedAt;
    }

    public void delete() {
        this.deletedAt = deletedAt;
    }

    public void undoDelete() {
        this.deletedAt = null;
    }
}
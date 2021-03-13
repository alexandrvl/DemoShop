package com.example.demo.common.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Audited
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true, of = {})
@ToString(callSuper = true)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditableEntity<ID extends Serializable> extends BaseVersionedEntity<ID> {

    @NotEmpty
    @Size(max = 100)
    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false, length = 100)
    private String createdBy;

    @NotNull
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @NotEmpty
    @Size(max = 100)
    @LastModifiedBy
    @Column(name = "last_modified_by", nullable = false, length = 100)
    private String lastModifiedBy;

    @NotNull
    @LastModifiedDate
    @Column(name = "last_modified_date", nullable = false)
    private LocalDateTime lastModifiedDate;

    public BaseAuditableEntity(Integer version,
            String createdBy, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate) {
        super(version);
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

}


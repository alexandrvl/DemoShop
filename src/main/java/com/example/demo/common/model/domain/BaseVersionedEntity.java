package com.example.demo.common.model.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

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
public abstract class BaseVersionedEntity<ID extends Serializable> extends BaseEntity<ID> {

    @Version
    @NotAudited
    private Integer version;

    public BaseVersionedEntity(Integer version) {
        this.version = version;
    }

    @Transient
    @Override
    public boolean isNew() {
        return getVersion() == null;
    }

}

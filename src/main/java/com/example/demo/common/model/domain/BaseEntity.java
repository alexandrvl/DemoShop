package com.example.demo.common.model.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;
import org.springframework.data.domain.Persistable;
import org.springframework.data.util.ProxyUtils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Audited
@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class BaseEntity<ID extends Serializable> implements Persistable<ID> {

    @Override
    public abstract ID getId();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        }

        var that = (BaseEntity<?>) obj;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() == null
                ? 17
                : 17 + getId().hashCode() * 31;
    }

}

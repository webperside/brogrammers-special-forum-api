package com.webperside.brogrammersspecialforum.listener;

import com.webperside.brogrammersspecialforum.models.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

public class EntityListener {

    @PrePersist
    public void onPrePersist(BaseEntity baseEntity){
        baseEntity.setCreatedAt(Instant.now());
    }

    @PreUpdate
    public void onPreUpdate(BaseEntity baseEntity){
        baseEntity.setModifiedAt(Instant.now());
    }
}

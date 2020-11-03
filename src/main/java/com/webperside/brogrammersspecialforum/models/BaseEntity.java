package com.webperside.brogrammersspecialforum.models;

import com.webperside.brogrammersspecialforum.listener.EntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@EntityListeners(EntityListener.class)
@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "timestamp")
    private Instant createdAt;

    @Column(name = "modified_at", columnDefinition = "timestamp")
    private Instant modifiedAt;


}

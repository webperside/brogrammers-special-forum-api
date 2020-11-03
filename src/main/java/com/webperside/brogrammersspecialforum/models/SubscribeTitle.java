package com.webperside.brogrammersspecialforum.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "subscribe_title")
public class SubscribeTitle {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "subscribe_id")
    private Integer id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fk_title_id")
    private Title title;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fk_user_id")
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "timestamp")
    private Instant createdAt;
}

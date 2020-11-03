package com.webperside.brogrammersspecialforum.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "follow_id")
    private Integer id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "from_user_id", nullable = false)
    private User fromUser;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "to_user_id", nullable = false)
    private User toUser;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "timestamp")
    private Instant createdAt;


}

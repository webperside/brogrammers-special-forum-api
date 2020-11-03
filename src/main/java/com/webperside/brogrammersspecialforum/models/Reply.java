package com.webperside.brogrammersspecialforum.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "reply")
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reply_id")
    private Integer id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fk_title_id")
    private Title title;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fk_user_id")
    private User user;

    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "MEDIUMBLOB")
    private String content;
}

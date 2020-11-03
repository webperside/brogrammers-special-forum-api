package com.webperside.brogrammersspecialforum.models;

import com.webperside.brogrammersspecialforum.enums.Trend;
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
@Table(name = "title")
public class Title extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "title_id")
    private Integer id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fk_user_id")
    private User user;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "url_name", nullable = false, unique = true, length = 100)
    private String urlName;

    @Column(name = "seen_count", nullable = false)
    private Integer seenCount;

    @Column(name = "is_trend", nullable = false, columnDefinition = "tinyint(4)")
    private Trend isTrend;
}

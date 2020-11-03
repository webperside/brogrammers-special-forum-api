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
@Table(name = "title_category")
public class TitleCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "title_category_id")
    private Integer id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fk_title_id")
    private Title title;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fk_category_id")
    private Category category;
}

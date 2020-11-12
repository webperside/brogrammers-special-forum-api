package com.webperside.brogrammersspecialforum.repository;

import com.webperside.brogrammersspecialforum.enums.Trend;
import com.webperside.brogrammersspecialforum.models.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {

    Page<Title> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Title> findAllByIsTrendOrderByCreatedAtDesc(Trend trend, Pageable pageable);
}

package com.webperside.brogrammersspecialforum.repository;

import com.webperside.brogrammersspecialforum.models.TitleCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleCategoryRepository extends JpaRepository<TitleCategory, Integer> {

    Page<TitleCategory> findAllByCategoryIdOrderByCreatedAtDesc(Integer categoryId, Pageable pageable);
}

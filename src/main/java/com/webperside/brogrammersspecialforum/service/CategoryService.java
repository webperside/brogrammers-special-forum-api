package com.webperside.brogrammersspecialforum.service;

import com.webperside.brogrammersspecialforum.dto.response.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<CategoryDto> getAll(Pageable pageable);
}

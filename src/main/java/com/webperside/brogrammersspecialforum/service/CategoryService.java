package com.webperside.brogrammersspecialforum.service;

import com.webperside.brogrammersspecialforum.dto.response.CategoryDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAll(Sort sort);
}

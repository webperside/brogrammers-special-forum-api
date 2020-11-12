package com.webperside.brogrammersspecialforum.service.impl;

import com.webperside.brogrammersspecialforum.dto.response.CategoryDto;
import com.webperside.brogrammersspecialforum.models.Category;
import com.webperside.brogrammersspecialforum.repository.CategoryRepository;
import com.webperside.brogrammersspecialforum.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public List<CategoryDto> getAll(Sort sort) {
        List<Category> categories = categoryRepository.findAll(sort);
        return categories.stream().map(CategoryDto::fromEntity).collect(Collectors.toList());
    }
}

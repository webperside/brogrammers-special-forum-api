package com.webperside.brogrammersspecialforum.controller;

import com.webperside.brogrammersspecialforum.annotations.PaginationAndSort;
import com.webperside.brogrammersspecialforum.dto.response.CategoryDto;
import com.webperside.brogrammersspecialforum.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @PaginationAndSort
    public Page<CategoryDto> getAll(@ApiIgnore Pageable pageable){
        return categoryService.getAll(pageable);
    }


}

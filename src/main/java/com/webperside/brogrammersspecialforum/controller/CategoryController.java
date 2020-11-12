package com.webperside.brogrammersspecialforum.controller;

import com.webperside.brogrammersspecialforum.annotations.PaginationAndSort;
import com.webperside.brogrammersspecialforum.annotations.Sort;
import com.webperside.brogrammersspecialforum.dto.response.CategoryDto;
import com.webperside.brogrammersspecialforum.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Sort
    public List<CategoryDto> getAll(@ApiIgnore org.springframework.data.domain.Sort sort){
        return categoryService.getAll(sort);
    }


}

package com.webperside.brogrammersspecialforum.controller;

import com.webperside.brogrammersspecialforum.annotations.PaginationAndSort;
import com.webperside.brogrammersspecialforum.dto.response.TitleListDto;
import com.webperside.brogrammersspecialforum.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/titles")
@RequiredArgsConstructor
public class TitleController {

    private final TitleService titleService;

    @GetMapping
    @PaginationAndSort
    public Page<TitleListDto> getAll(@ApiIgnore Pageable pageable,
                                     @RequestParam(name = "categoryId",required = false) Integer categoryId){
        return titleService.getAll(pageable, categoryId);
    }

    @GetMapping("/trend")
    @PaginationAndSort
    public Page<TitleListDto> getAllTrend(@ApiIgnore Pageable pageable){
        return titleService.getAllTrend(pageable);
    }

}
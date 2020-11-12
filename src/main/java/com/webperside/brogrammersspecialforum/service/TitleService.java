package com.webperside.brogrammersspecialforum.service;

import com.webperside.brogrammersspecialforum.dto.response.TitleListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TitleService {

    Page<TitleListDto> getAll(Pageable pageable, Integer categoryId);

    Page<TitleListDto> getAllTrend(Pageable pageable);
}

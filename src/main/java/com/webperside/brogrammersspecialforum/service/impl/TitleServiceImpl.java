package com.webperside.brogrammersspecialforum.service.impl;

import com.webperside.brogrammersspecialforum.dto.response.TitleListDto;
import com.webperside.brogrammersspecialforum.enums.Trend;
import com.webperside.brogrammersspecialforum.models.Title;
import com.webperside.brogrammersspecialforum.models.TitleCategory;
import com.webperside.brogrammersspecialforum.repository.TitleCategoryRepository;
import com.webperside.brogrammersspecialforum.repository.TitleRepository;
import com.webperside.brogrammersspecialforum.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

    private final TitleRepository titleRepository;
    private final TitleCategoryRepository titleCategoryRepository;

    @Override
    public Page<TitleListDto> getAll(Pageable pageable, Integer categoryId) {
        Page<Title> titles;
        if(categoryId == null) {
            titles = titleRepository.findAllByOrderByCreatedAtDesc(pageable);
        } else {
            titles = titleCategoryRepository.findAllByCategoryIdOrderByCreatedAtDesc(categoryId,pageable)
                    .map(TitleCategory::getTitle);
        }
        return titles.map(TitleListDto::fromEntity);
    }

    @Override
    public Page<TitleListDto> getAllTrend(Pageable pageable) {
        Page<Title> titles = titleRepository.findAllByIsTrendOrderByCreatedAtDesc(Trend.TREND, pageable);
        return titles.map(TitleListDto::fromEntity);
    }
}

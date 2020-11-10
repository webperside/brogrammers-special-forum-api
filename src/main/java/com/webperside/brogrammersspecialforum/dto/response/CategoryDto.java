package com.webperside.brogrammersspecialforum.dto.response;

import com.webperside.brogrammersspecialforum.models.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

    private Integer id;
    private String name;
    private String urlName;
    private Integer titlesNumber;

    public static CategoryDto fromEntity(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .urlName(category.getUrlName())
                .titlesNumber(category.getTitles().size())
                .build();
    }
}

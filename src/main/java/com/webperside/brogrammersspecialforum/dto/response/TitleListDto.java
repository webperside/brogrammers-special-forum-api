package com.webperside.brogrammersspecialforum.dto.response;

import com.webperside.brogrammersspecialforum.models.Title;
import com.webperside.brogrammersspecialforum.models.TitleCategory;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class TitleListDto {

    private Integer id;
    private String name;
    private String urlName;
    private Integer seenCount;
    private Byte isTrend;
    private Integer repliesNumber;
    private String categories;
    private Instant createdAt;
    private TitleList_UserDto user;

    private static String getCategories(List<TitleCategory> categories){
        String fullCategories = categories.stream()
                .map(titleCategory -> titleCategory.getCategory().getName())
                .collect(Collectors.joining(","));
        return fullCategories.substring(0,Math.min(fullCategories.length(),20));
    }

    public static TitleListDto fromEntity(Title title){
        return TitleListDto.builder()
                .id(title.getId())
                .name(title.getName().substring(0, Math.min(title.getName().length(), 50)))
                .urlName(title.getUrlName())
                .seenCount(title.getSeenCount())
                .isTrend(title.getIsTrend().getValue())
                .repliesNumber(title.getReplies().size())
                .categories(getCategories(title.getCategories()))
                .createdAt(title.getCreatedAt())
                .user(TitleList_UserDto.builder()
                        .avatar(title.getUser().getAvatar())
                        .username(title.getUser().getUsername())
                        .fullName(title.getUser().getFullName())
                        .build()
                )
                .build();
    }


    @Data
    @Builder
    private static class TitleList_UserDto{
        private String avatar;
        private String username;
        private String fullName;
    }
}

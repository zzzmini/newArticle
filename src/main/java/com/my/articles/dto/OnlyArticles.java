package com.my.articles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OnlyArticles {
    private Long id;
    private String title;
    private String content;

    public static OnlyArticles fromArticleDTO(ArticleDTO dto) {
        return new OnlyArticles(
                dto.getId(),
                dto.getTitle(),
                dto.getContent()
        );
    }
}

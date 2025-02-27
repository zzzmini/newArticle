package com.my.articles.dto;

import com.my.articles.entity.Article;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private List<CommentDTO> comments = new ArrayList<>();

    // Entity -> DTO
    public static ArticleDTO fromEntity(Article article) {
        return new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getComments()
                        .stream()
                        .map(x -> CommentDTO.fromEntity(x))
                        .toList()
        );
    }

    // DTO -> Entity
    public static Article fromDto(ArticleDTO dto) {
        Article article = new Article();
        article.setId(dto.getId());
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        return article;
    }
}

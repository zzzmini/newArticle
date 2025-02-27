package com.my.articles.service;

import com.my.articles.dao.ArticleDAO;
import com.my.articles.dto.ArticleDTO;
import com.my.articles.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleDAO dao;

    public List<ArticleDTO> getAllArticles() {
        List<Article> articleList = dao.getAllArticles();

        if (ObjectUtils.isEmpty(articleList)) {
            // 비어있으면
            return Collections.emptyList();
        }
        return articleList
                .stream()
                .map(x -> ArticleDTO.fromEntity(x))
                .toList();
    }

    public void insertArticle(ArticleDTO dto) {
        dao.insertArticle(ArticleDTO.fromDto(dto));
    }

    public ArticleDTO getOneArticle(Long id) {
        Article article = dao.getOneArticle(id);

        if(ObjectUtils.isEmpty(article)) return null;
        return ArticleDTO.fromEntity(article);
    }

    public void deleteArticle(Long id) {
        dao.deleteArticle(id);
    }

    public void updateArticle(ArticleDTO dto) {
        dao.updateArticle(ArticleDTO.fromDto(dto));
    }
}

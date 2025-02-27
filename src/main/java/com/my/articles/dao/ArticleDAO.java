package com.my.articles.dao;

import com.my.articles.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// Article Entity 와  article Table 간 ORM 처리 용도
// DAO(Data Access Object)
@Component
@Transactional
public class ArticleDAO {
    @Autowired
    EntityManager em;

    public List<Article> getAllArticles() {
        String sql = "SELECT a FROM Article AS a " +
                "ORDER BY a.id DESC";
        List<Article> articles = em.createQuery(sql).getResultList();
        return articles;
    }

    public void insertArticle(Article article) {
        em.persist(article);
    }

    public Article getOneArticle(Long id) {
        return em.find(Article.class, id);
    }

    public void deleteArticle(Long id) {
        // id 로 찾기
        Article article = em.find(Article.class, id);
        em.remove(article);
    }

    public void updateArticle(Article article) {
        // 가져온 article에서 id로 원본을 찾아요
        Article original = em.find(Article.class, article.getId());
        // 변경 하면 끝 - Dirty Checking
        original.setTitle(article.getTitle());
        original.setContent(article.getContent());
    }
}

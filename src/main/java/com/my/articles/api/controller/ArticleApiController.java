package com.my.articles.api.controller;

import com.my.articles.api.exception.BadRequestException;
import com.my.articles.dto.ArticleDTO;
import com.my.articles.dto.CommentDTO;
import com.my.articles.dto.OnlyArticles;
import com.my.articles.service.ArticleService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleApiController {
    private final ArticleService articleService;

    public ArticleApiController(ArticleService articleService) {
        this.articleService = articleService;
    }


    // 1. 전체 게시글 리스트 가져오기
    @GetMapping("/api/articles")
    public ResponseEntity<List<OnlyArticles>> findAllArticles() {
        List<ArticleDTO> articleDTOList = articleService.getAllArticles();
        List<OnlyArticles> onlyArticleDto =  articleDTOList
                .stream()
                .map(x -> OnlyArticles.fromArticleDTO(x))
                .toList();

        if (ObjectUtils.isEmpty(onlyArticleDto)) {
            throw new BadRequestException("게시글이 없습니다.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(onlyArticleDto);
        }
    }

    // 2. 특정 게시글의 댓글 전체 리스트 가져오기
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDTO>>
        findByArticleComments(@PathVariable("articleId")Long articleId) {
        // 1. articleId 존재 확인
        ArticleDTO articleDTO = articleService.getOneArticle(articleId);

        if (ObjectUtils.isEmpty(articleDTO)) {
            throw new BadRequestException("해당 아이디의 게시글 없음");
        }

        // 2. 댓글 리스트 가져오기
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleDTO.getComments());
    }
}

package com.my.articles.service;

import com.my.articles.dao.CommentDAO;
import com.my.articles.dto.CommentDTO;
import com.my.articles.entity.Comment;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommentService {
    private final CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public Long deleteComment(Long id) {
        return commentDAO.deleteComment(id);
    }

    public void insertComment(Long articleId, CommentDTO dto) {
        commentDAO.insetComment(articleId,
                CommentDTO.fromDto(dto));
    }

    public Map<String, Object> findByCommentId(Long commentId) {
        Comment comment = commentDAO.findByCommentId(commentId);
        Map<String, Object> data = new HashMap<>();
        if (ObjectUtils.isEmpty(comment)) {
            data.put("articleId", null);
            data.put("dto", null);
        } else {
            data.put("articleId", comment.getArticle().getId());
            data.put("dto", CommentDTO.fromEntity(comment));
        }
        return data;
    }

    public void updateComment(CommentDTO dto) {
        commentDAO.updateComment(CommentDTO.fromDto(dto));
    }
}

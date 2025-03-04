package com.my.articles.service;

import com.my.articles.dao.CommentDAO;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }
}

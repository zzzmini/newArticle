package com.my.articles.controller;

import com.my.articles.dto.CommentDTO;
import com.my.articles.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/articles")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 삭제 작업
    @GetMapping("comments/{id}")
    public String deleteComment(@PathVariable("id") Long id) {
        // 건네받은 댓글 아이디로 삭제 요청
        Long articleId = commentService.deleteComment(id);
        return "redirect:/articles/" + articleId;
    }

    // 댓글 입력 작업
    @PostMapping("{id}/comments")
    public String insertComment(CommentDTO dto,
                                @PathVariable("id") Long articleId) {
        System.out.println(dto);
        commentService.insertComment(articleId, dto);
        return "redirect:/articles/" + articleId;
    }

    // 댓글 1개 조회
    @GetMapping("comments/view/{commentId}")
    public String updateCommentView(@PathVariable("commentId")Long commentId,
                                    Model model) {
        // 댓글 읽어와서 view에 전달하기
        Map<String, Object> data = commentService.findByCommentId(commentId);
        model.addAttribute("articleId", data.get("articleId"));
        model.addAttribute("dto", data.get("dto"));
        return "/articles/update_comment";
    }

    // 댓글 수정
    @PostMapping("{articleId}/comments/{commentId}")
    public String updateComment(
            CommentDTO dto,
            @PathVariable("articleId")Long articleId,
            @PathVariable("commentId")Long commentId
    ) {
        System.out.println(dto);
        commentService.updateComment(dto);
        return "redirect:/articles/" + articleId;
    }
}

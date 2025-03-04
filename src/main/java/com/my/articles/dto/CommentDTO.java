package com.my.articles.dto;

import com.my.articles.entity.Article;
import com.my.articles.entity.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long commentId;
    private String nickname;
    private String body;

    public static CommentDTO fromEntity(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }

    public static Comment fromDto(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setId(dto.getCommentId());
        comment.setNickname(dto.getNickname());
        comment.setBody(dto.getBody());
        return comment;
    }
}

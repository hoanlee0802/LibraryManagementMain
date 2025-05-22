package hoanvt.librarymanagementmain.dto;

import lombok.Data;

@Data
public class CommentRequestDTO {
    private String content;
    private Long postId;
    private Long userId;
}


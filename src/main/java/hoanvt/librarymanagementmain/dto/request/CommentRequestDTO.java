package hoanvt.librarymanagementmain.dto.request;

import lombok.Data;

@Data
public class CommentRequestDTO {
    private String content;
    private Long postId;
    private Long userId;
}


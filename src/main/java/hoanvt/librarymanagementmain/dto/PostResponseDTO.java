package hoanvt.librarymanagementmain.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long userId;
    private String username;
    private Long bookId;
    private String bookTitle;
    private Set<Long> likedUserIds;
    private int likeCount;
    // Optionally add: comments, etc.
}

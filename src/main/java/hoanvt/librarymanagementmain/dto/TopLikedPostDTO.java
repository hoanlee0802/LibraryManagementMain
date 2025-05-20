package hoanvt.librarymanagementmain.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TopLikedPostDTO {
    private Long id;
    private String title;
    private String content;
    private int likeCount;
    private String username;
    private LocalDateTime createdAt;
}

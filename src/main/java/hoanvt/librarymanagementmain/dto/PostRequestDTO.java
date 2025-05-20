package hoanvt.librarymanagementmain.dto;

import lombok.Data;

@Data
public class PostRequestDTO {
    private String title;
    private String content;
    private Long bookId;  // optional: link post to a book
}


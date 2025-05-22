package hoanvt.librarymanagementmain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PostRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Long bookId;
}


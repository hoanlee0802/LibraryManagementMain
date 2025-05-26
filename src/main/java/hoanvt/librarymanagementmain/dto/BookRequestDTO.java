package hoanvt.librarymanagementmain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookRequestDTO {
//    @NotBlank
    private String code;

//    @NotBlank
    private String title;

//    @NotBlank
    private String authors;

//    @NotBlank
    private String publisher;

//    @NotNull
//    @Min(value = 1)
    private Integer pageCount;

//    @NotBlank
    private String printType;

//    @NotBlank
    private String language;

    private String description;

//    @NotNull
//    @Min(value = 1)
    private Integer quantity;
}

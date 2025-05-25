package hoanvt.librarymanagementmain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookSearchRequestDTO {

    private int page = 0;

    private int size = 10;

    private String code;

    private String title;

    private String authors;

    private String publisher;

    private String printType;

    private String language;
}

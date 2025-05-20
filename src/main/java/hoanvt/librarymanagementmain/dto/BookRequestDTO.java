package hoanvt.librarymanagementmain.dto;

import lombok.Data;

@Data
public class BookRequestDTO {
    private String code;
    private String title;
    private String authors;
    private String publisher;
    private Integer pageCount;
    private String printType;
    private String language;
    private String description;
    private Integer quantity;
}

package hoanvt.librarymanagementmain.dto.request;

import lombok.Data;

@Data
public class BookSearchRequestDTO {

//    private int page = 0;
//
//    private int size = 10;

    private String code;

    private String title;

    private String authors;

    private String publisher;
//
//    private String printType;
//
    private String language;


}

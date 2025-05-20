package hoanvt.librarymanagementmain.dto;

import lombok.Data;

@Data
public class CategoryStatisticsDTO {
    private String categoryCode;
    private String categoryName;
    private int bookCount;
}

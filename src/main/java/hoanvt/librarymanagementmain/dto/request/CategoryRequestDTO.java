package hoanvt.librarymanagementmain.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoryRequestDTO {

    @NotBlank
    private String categoryCode;

    @NotBlank
    private String categoryName;
}

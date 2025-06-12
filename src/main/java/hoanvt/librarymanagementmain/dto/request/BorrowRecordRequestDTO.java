package hoanvt.librarymanagementmain.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BorrowRecordRequestDTO {
    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;

    @NotNull
    private LocalDateTime borrowDate;

    private LocalDateTime returnDate;

    @NotNull
    @Min(1)
    private Integer quantity;
}

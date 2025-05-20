package hoanvt.librarymanagementmain.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BorrowRecordRequestDTO {
    private Long userId;
    private Long bookId;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private Integer quantity;
}

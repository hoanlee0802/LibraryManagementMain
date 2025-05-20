package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.BorrowRecordRequestDTO;
import hoanvt.librarymanagementmain.dto.BorrowRecordResponseDTO;

import java.util.List;

public interface BorrowRecordService {
    List<BorrowRecordResponseDTO> getMyBorrowRecords(Long userId);
    BorrowRecordResponseDTO createBorrowRecord(BorrowRecordRequestDTO dto);
    BorrowRecordResponseDTO getBorrowRecordById(Long id, Long userId);
    BorrowRecordResponseDTO updateBorrowRecord(Long id, BorrowRecordRequestDTO dto, Long userId);
    void deleteBorrowRecord(Long id, Long userId);
}

package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.request.BorrowRecordRequestDTO;
import hoanvt.librarymanagementmain.dto.response.BorrowRecordResponseDTO;

import java.util.List;

public interface BorrowRecordService {
    List<BorrowRecordResponseDTO> getMyBorrowRecords(Long userId);
    BorrowRecordResponseDTO createBorrowRecord(BorrowRecordRequestDTO dto);
    BorrowRecordResponseDTO getBorrowRecordById(Long id, Long userId);
    BorrowRecordResponseDTO updateBorrowRecord(Long id, BorrowRecordRequestDTO dto, Long userId);
    void deleteBorrowRecord(Long id, Long userId);
}

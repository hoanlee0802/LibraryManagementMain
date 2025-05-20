package hoanvt.librarymanagementmain.repository;

import hoanvt.librarymanagementmain.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
}
package hoanvt.librarymanagementmain.repository;

import hoanvt.librarymanagementmain.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByUserIdOrderByReturnDateAscBorrowDateDesc(Long userId);
}
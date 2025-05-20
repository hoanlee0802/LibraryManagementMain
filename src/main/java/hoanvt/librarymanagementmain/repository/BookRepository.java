package hoanvt.librarymanagementmain.repository;

import hoanvt.librarymanagementmain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByCode(String code);

}
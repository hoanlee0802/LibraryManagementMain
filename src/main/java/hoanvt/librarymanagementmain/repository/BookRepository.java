package hoanvt.librarymanagementmain.repository;

import hoanvt.librarymanagementmain.dto.BookSearchRequestDTO;
import hoanvt.librarymanagementmain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
//    boolean existsByCode(String code);
    Optional<Book> findByCode(String code);

@Query("SELECT b FROM Book b WHERE " +
           "(:code IS NULL OR b.code LIKE %:code%) AND " +
           "(:title IS NULL OR b.title LIKE %:title%)")
    Page<Book> search(String code, String title,
                                    Pageable pageable);}
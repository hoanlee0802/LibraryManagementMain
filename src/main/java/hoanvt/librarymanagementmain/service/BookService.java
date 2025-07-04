package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.request.BookRequestDTO;
import hoanvt.librarymanagementmain.dto.response.BookResponseDTO;
import hoanvt.librarymanagementmain.dto.request.BookSearchRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookResponseDTO createBook(BookRequestDTO dto);
    BookResponseDTO getBookById(Long id);
    BookResponseDTO updateBook(Long id, BookRequestDTO dto);
//    void deleteBook(Long id);
    List<BookResponseDTO> getAllBooks();

    Page<BookResponseDTO> searchBooks(BookSearchRequestDTO request, Pageable pageable);
}
package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.BookRequestDTO;
import hoanvt.librarymanagementmain.dto.BookResponseDTO;

import java.util.List;

public interface BookService {
    BookResponseDTO createBook(BookRequestDTO dto);
    BookResponseDTO getBookById(Long id);
    BookResponseDTO updateBook(Long id, BookRequestDTO dto);
    void deleteBook(Long id);
    List<BookResponseDTO> getAllBooks();
}
package hoanvt.librarymanagementmain.service.impl;

import hoanvt.librarymanagementmain.dto.BookRequestDTO;
import hoanvt.librarymanagementmain.dto.BookResponseDTO;
import hoanvt.librarymanagementmain.dto.BookSearchRequestDTO;
import hoanvt.librarymanagementmain.entity.Book;
import hoanvt.librarymanagementmain.repository.BookRepository;
import hoanvt.librarymanagementmain.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private BookResponseDTO toResponseDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setCode(book.getCode());
        dto.setTitle(book.getTitle());
        dto.setAuthors(book.getAuthor());
        dto.setPublisher(book.getPublisher());
        dto.setPageCount(book.getPageCount());
        dto.setPrintType(book.getPrintType());
        dto.setLanguage(book.getLanguage());
        dto.setDescription(book.getDescription());
        dto.setQuantity(book.getQuantity());
        return dto;
    }

    private Book toEntity(BookRequestDTO dto) {
        Book book = new Book();
        book.setCode(dto.getCode());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthors());
        book.setPublisher(dto.getPublisher());
        book.setPageCount(dto.getPageCount());
        book.setPrintType(dto.getPrintType());
        book.setLanguage(dto.getLanguage());
        book.setDescription(dto.getDescription());
        book.setQuantity(dto.getQuantity());
        return book;
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO dto) {
        if (bookRepository.findByCode(dto.getCode()).isPresent()) {
            throw new RuntimeException("Book code already exists");
        }
        Book book = toEntity(dto);
        book = bookRepository.save(book);
        return toResponseDTO(book);
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        return bookOpt.map(this::toResponseDTO).orElse(null);
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            // Update fields
            book.setCode(dto.getCode());
            book.setTitle(dto.getTitle());
            book.setAuthor(dto.getAuthors());
            book.setPublisher(dto.getPublisher());
            book.setPageCount(dto.getPageCount());
            book.setPrintType(dto.getPrintType());
            book.setLanguage(dto.getLanguage());
            book.setDescription(dto.getDescription());
            book.setQuantity(dto.getQuantity());
            book = bookRepository.save(book);
            return toResponseDTO(book);
        }
        return null;
    }

//    @Override
//    public void deleteBook(Long id) {
//        bookRepository.deleteById(id);
//    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookResponseDTO> searchBooks(BookSearchRequestDTO requestDTO) {
        Pageable pageable = PageRequest.of(requestDTO.getPage(), requestDTO.getSize());
        return bookRepository.search(
                requestDTO.getCode(),
                requestDTO.getAuthors(),
                pageable
        ).map(this::toResponseDTO);
    }
}


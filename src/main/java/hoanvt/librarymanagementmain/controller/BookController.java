package hoanvt.librarymanagementmain.controller;

import hoanvt.librarymanagementmain.dto.ApiResponse;
import hoanvt.librarymanagementmain.dto.BookRequestDTO;
import hoanvt.librarymanagementmain.dto.BookResponseDTO;
import hoanvt.librarymanagementmain.dto.BookSearchRequestDTO;
import hoanvt.librarymanagementmain.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;



    @PostMapping("/create")
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO bookDto) {
        BookResponseDTO createdBook = bookService.createBook(bookDto);
        return ResponseEntity.ok(createdBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        BookResponseDTO book = bookService.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDTO bookDto) {
        BookResponseDTO updatedBook = bookService.updateBook(id, bookDto);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<BookResponseDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

//    @PostMapping("/search")
//    public ResponseEntity<Page<BookResponseDTO>> searchBooks(@Valid @RequestBody BookSearchRequestDTO requestDTO) {
//        Page<BookResponseDTO> books = bookService.searchBooks(requestDTO);
//        return ResponseEntity.ok(books);
//    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ROLE_VIEW_BOOK')")
    public ResponseEntity<ApiResponse<Page<BookResponseDTO>>> searchBooks(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String language,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        BookSearchRequestDTO searchRequest = new BookSearchRequestDTO();
        searchRequest.setCode(code);
        searchRequest.setTitle(title);
        searchRequest.setAuthors(author);
        searchRequest.setPublisher(publisher);
        searchRequest.setLanguage(language);

        Pageable pageable = PageRequest.of(page, size);
        Page<BookResponseDTO> result = bookService.searchBooks(searchRequest, pageable);
        return ResponseEntity.ok(ApiResponse.success(result));
    }


}

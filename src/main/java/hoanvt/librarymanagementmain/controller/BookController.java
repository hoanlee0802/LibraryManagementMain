package hoanvt.librarymanagementmain.controller;

import hoanvt.librarymanagementmain.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return new Book();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return new Book();
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return new ArrayList<>();
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return new Book();
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        log.info("Deleting book with id {}", id);
    }
}
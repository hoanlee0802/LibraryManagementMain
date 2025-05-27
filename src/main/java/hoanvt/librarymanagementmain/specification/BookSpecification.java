package hoanvt.librarymanagementmain.specification;

import hoanvt.librarymanagementmain.dto.BookSearchRequestDTO;
import hoanvt.librarymanagementmain.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class BookSpecification {
    public static Specification<Book> build(BookSearchRequestDTO request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getCode() != null && !request.getCode().isEmpty())
                predicates.add(cb.like(root.get("code"), "%" + request.getCode() + "%"));
            if (request.getTitle() != null && !request.getTitle().isEmpty())
                predicates.add(cb.like(root.get("title"), "%" + request.getTitle() + "%"));
            if (request.getAuthors() != null && !request.getAuthors().isEmpty())
                predicates.add(cb.like(root.get("author"), "%" + request.getAuthors() + "%"));
            if (request.getPublisher() != null && !request.getPublisher().isEmpty())
                predicates.add(cb.like(root.get("publisher"), "%" + request.getPublisher() + "%"));
            if (request.getLanguage() != null && !request.getLanguage().isEmpty())
                predicates.add(cb.like(root.get("language"), "%" + request.getLanguage() + "%"));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

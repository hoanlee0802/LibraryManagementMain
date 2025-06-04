package hoanvt.librarymanagementmain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String title;
    private String author;
    private String publisher;
    private Integer pageCount;
    private String printType;
    private String language;
    private String description;
    private Integer quantity;
    private Integer isActive;
    private Integer isDeleted;

    @ManyToMany
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @OneToMany(mappedBy = "book")
    private Set<BorrowRecord> borrowRecords;

    @OneToMany(mappedBy = "book")
    private Set<Post> posts;
}
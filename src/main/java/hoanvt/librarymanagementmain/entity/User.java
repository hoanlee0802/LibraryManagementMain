package hoanvt.librarymanagementmain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String identityNumber;
    private Integer age;
    private Date birthday;
    private String address;

    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime updatedDate;
    private String updatedBy;
    private Integer isActive;
    private Integer isDeleted;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_group_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_group_id"))
    private Set<RoleGroup> roleGroups;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user")
    private Set<BorrowRecord> borrowRecords;

    @ManyToMany
    @JoinTable(name = "favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<Post> favoritePosts;
}
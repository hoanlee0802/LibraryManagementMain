package hoanvt.librarymanagementmain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "role_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleGroupCode;
    private String roleGroupName;
    private String description;

    @ManyToMany(mappedBy = "roleGroups")
    private Set<User> users;

    @ManyToMany
    @JoinTable(name = "role_group_permission",
            joinColumns = @JoinColumn(name = "role_group_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;
}

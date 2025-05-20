package hoanvt.librarymanagementmain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String permissionCode;
    private String permissionName;
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<RoleGroup> roleGroups;
}
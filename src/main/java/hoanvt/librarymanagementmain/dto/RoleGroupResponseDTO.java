package hoanvt.librarymanagementmain.dto;

import lombok.Data;
import java.util.Set;


@Data
public class RoleGroupResponseDTO {
    private Long id;
    private String roleGroupCode;
    private String roleGroupName;
    private String description;
    private Set<RoleGroupUserDTO> users;
    private Set<RoleGroupPermissionDTO> permissions;
}
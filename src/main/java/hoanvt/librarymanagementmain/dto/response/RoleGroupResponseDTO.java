package hoanvt.librarymanagementmain.dto.response;

import hoanvt.librarymanagementmain.dto.RoleGroupPermissionDTO;
import hoanvt.librarymanagementmain.dto.RoleGroupUserDTO;
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
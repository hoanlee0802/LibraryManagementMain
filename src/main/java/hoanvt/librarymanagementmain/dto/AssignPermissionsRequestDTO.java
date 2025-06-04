package hoanvt.librarymanagementmain.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AssignPermissionsRequestDTO {
    private Long roleGroupId;
    private Set<Long> permissionIds;
}
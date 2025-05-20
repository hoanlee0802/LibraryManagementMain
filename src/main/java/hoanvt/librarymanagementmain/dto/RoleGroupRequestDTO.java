package hoanvt.librarymanagementmain.dto;

import lombok.Data;
import java.util.Set;

@Data
public class RoleGroupRequestDTO {
    private String roleGroupCode;
    private String roleGroupName;
    private String description;
    private Set<Long> userIds;
    private Set<Long> permissionIds;
}

package hoanvt.librarymanagementmain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Set;

@Data
public class RoleGroupRequestDTO {
    @NotBlank
    private String roleGroupCode;

    @NotBlank
    private String roleGroupName;

    @NotNull
    private Set<Long> userIds;

    @NotNull
    private Set<Long> permissionIds;

    @NotBlank
    private String description;
}

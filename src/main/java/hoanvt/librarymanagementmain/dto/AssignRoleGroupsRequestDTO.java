package hoanvt.librarymanagementmain.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AssignRoleGroupsRequestDTO {
    private Long userId;
    private Set<Long> roleGroupIds;
}
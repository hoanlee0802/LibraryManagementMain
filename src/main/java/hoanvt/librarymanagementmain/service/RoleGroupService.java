package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.RoleGroupRequestDTO;
import hoanvt.librarymanagementmain.dto.RoleGroupResponseDTO;

import java.util.List;
import java.util.Set;

public interface RoleGroupService {
    RoleGroupResponseDTO createRoleGroup(RoleGroupRequestDTO dto);
    RoleGroupResponseDTO getRoleGroupById(Long id);
    RoleGroupResponseDTO updateRoleGroup(Long id, RoleGroupRequestDTO dto);
    void deleteRoleGroup(Long id);
    List<RoleGroupResponseDTO> getAllRoleGroups();

    void assignPermissionsToRoleGroup(Long id, Set<Long> permissionIds);

    void addPermissionToRoleGroup(Long id, Long permissionId);

    void removePermissionFromRoleGroup(Long id, Long permissionId);
}

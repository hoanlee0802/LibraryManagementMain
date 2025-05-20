package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.RoleGroupRequestDTO;
import hoanvt.librarymanagementmain.dto.RoleGroupResponseDTO;

import java.util.List;

public interface RoleGroupService {
    RoleGroupResponseDTO createRoleGroup(RoleGroupRequestDTO dto);
    RoleGroupResponseDTO getRoleGroupById(Long id);
    RoleGroupResponseDTO updateRoleGroup(Long id, RoleGroupRequestDTO dto);
    void deleteRoleGroup(Long id);
    List<RoleGroupResponseDTO> getAllRoleGroups();
}

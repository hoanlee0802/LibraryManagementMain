package hoanvt.librarymanagementmain.service.impl;

import hoanvt.librarymanagementmain.dto.RoleGroupPermissionDTO;
import hoanvt.librarymanagementmain.dto.request.RoleGroupRequestDTO;
import hoanvt.librarymanagementmain.dto.response.RoleGroupResponseDTO;
import hoanvt.librarymanagementmain.dto.RoleGroupUserDTO;
import hoanvt.librarymanagementmain.entity.Permission;
import hoanvt.librarymanagementmain.entity.RoleGroup;
import hoanvt.librarymanagementmain.entity.User;
import hoanvt.librarymanagementmain.repository.PermissionRepository;
import hoanvt.librarymanagementmain.repository.RoleGroupRepository;
import hoanvt.librarymanagementmain.repository.UserRepository;
import hoanvt.librarymanagementmain.service.RoleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleGroupServiceImpl implements RoleGroupService {

    @Autowired
    private RoleGroupRepository roleGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    private RoleGroupUserDTO toUserDTO(User user) {
        RoleGroupUserDTO dto = new RoleGroupUserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullname(user.getFullName());
        return dto;
    }

    private RoleGroupPermissionDTO toPermissionDTO(Permission permission) {
        RoleGroupPermissionDTO dto = new RoleGroupPermissionDTO();
        dto.setId(permission.getId());
        dto.setCode(permission.getPermissionCode());
        dto.setName(permission.getPermissionName());
        return dto;
    }

    private RoleGroupResponseDTO toResponseDTO(RoleGroup roleGroup) {
        RoleGroupResponseDTO dto = new RoleGroupResponseDTO();
        dto.setId(roleGroup.getId());
        dto.setRoleGroupCode(roleGroup.getRoleGroupCode());
        dto.setRoleGroupName(roleGroup.getRoleGroupName());
        dto.setDescription(roleGroup.getDescription());

        if (roleGroup.getUsers() != null) {
            Set<RoleGroupUserDTO> userDTOs = roleGroup.getUsers()
                    .stream().map(this::toUserDTO).collect(Collectors.toSet());
            dto.setUsers(userDTOs);
        }
        if (roleGroup.getPermissions() != null) {
            Set<RoleGroupPermissionDTO> permDTOs = roleGroup.getPermissions()
                    .stream().map(this::toPermissionDTO).collect(Collectors.toSet());
            dto.setPermissions(permDTOs);
        }
        return dto;
    }

    @Override
    public RoleGroupResponseDTO createRoleGroup(RoleGroupRequestDTO dto) {
        if (roleGroupRepository.findByRoleGroupCode(dto.getRoleGroupCode()).isPresent()) {
            throw new RuntimeException("Role group code already exists");
        }
        RoleGroup rg = new RoleGroup();
        rg.setRoleGroupCode(dto.getRoleGroupCode());
        rg.setRoleGroupName(dto.getRoleGroupName());
        rg.setDescription(dto.getDescription());

        if (dto.getUserIds() != null) {
            Set<User> users = new HashSet<>(userRepository.findAllById(dto.getUserIds()));
            rg.setUsers(users);
        }
        if (dto.getPermissionIds() != null) {
            Set<Permission> perms = new HashSet<>(permissionRepository.findAllById(dto.getPermissionIds()));
            rg.setPermissions(perms);
        }
        rg = roleGroupRepository.save(rg);
        return toResponseDTO(rg);
    }

    @Override
    public RoleGroupResponseDTO getRoleGroupById(Long id) {
        RoleGroup rg = roleGroupRepository.findById(id).orElse(null);
        return rg == null ? null : toResponseDTO(rg);
    }

    @Override
    public RoleGroupResponseDTO updateRoleGroup(Long id, RoleGroupRequestDTO dto) {
        Optional<RoleGroup> rgOpt = roleGroupRepository.findById(id);
        if (rgOpt.isPresent()) {
            RoleGroup rg = rgOpt.get();
            rg.setRoleGroupCode(dto.getRoleGroupCode());
            rg.setRoleGroupName(dto.getRoleGroupName());
            rg.setDescription(dto.getDescription());
            if (dto.getUserIds() != null) {
                Set<User> users = new HashSet<>(userRepository.findAllById(dto.getUserIds()));
                rg.setUsers(users);
            }
            if (dto.getPermissionIds() != null) {
                Set<Permission> perms = new HashSet<>(permissionRepository.findAllById(dto.getPermissionIds()));
                rg.setPermissions(perms);
            }
            rg = roleGroupRepository.save(rg);
            return toResponseDTO(rg);
        }
        return null;
    }

    @Override
    public void deleteRoleGroup(Long id) {
        roleGroupRepository.deleteById(id);
    }

    @Override
    public List<RoleGroupResponseDTO> getAllRoleGroups() {
        return roleGroupRepository.findAll().stream()
                .map(this::toResponseDTO).collect(Collectors.toList());
    }

    // Gán danh sách permission cho role group (GHI ĐÈ)
    public void assignPermissionsToRoleGroup(Long roleGroupId, Set<Long> permissionIds) {
        RoleGroup roleGroup = roleGroupRepository.findById(roleGroupId)
                .orElseThrow(() -> new RuntimeException("Role group not found"));

        Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(permissionIds));
        roleGroup.setPermissions(permissions);
        roleGroupRepository.save(roleGroup);
    }

    // Thêm 1 quyền vào role group (KHÔNG ghi đè)
    public void addPermissionToRoleGroup(Long roleGroupId, Long permissionId) {
        RoleGroup roleGroup = roleGroupRepository.findById(roleGroupId)
                .orElseThrow(() -> new RuntimeException("Role group not found"));
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        roleGroup.getPermissions().add(permission);
        roleGroupRepository.save(roleGroup);
    }

    // Xóa 1 quyền khỏi role group
    public void removePermissionFromRoleGroup(Long roleGroupId, Long permissionId) {
        RoleGroup roleGroup = roleGroupRepository.findById(roleGroupId)
                .orElseThrow(() -> new RuntimeException("Role group not found"));
        roleGroup.getPermissions().removeIf(p -> p.getId().equals(permissionId));
        roleGroupRepository.save(roleGroup);
    }
}

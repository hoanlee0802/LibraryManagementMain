package hoanvt.librarymanagementmain.controller;

import hoanvt.librarymanagementmain.dto.request.AssignPermissionsRequestDTO;
import hoanvt.librarymanagementmain.dto.request.RoleGroupRequestDTO;
import hoanvt.librarymanagementmain.dto.response.RoleGroupResponseDTO;
import hoanvt.librarymanagementmain.service.RoleGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role-groups")
public class RoleGroupController {

    @Autowired
    private RoleGroupService roleGroupService;

    @PostMapping("/create")
    public ResponseEntity<RoleGroupResponseDTO> create(@Valid @RequestBody RoleGroupRequestDTO dto) {
        return ResponseEntity.ok(roleGroupService.createRoleGroup(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleGroupResponseDTO> getById(@PathVariable Long id) {
        RoleGroupResponseDTO dto = roleGroupService.getRoleGroupById(id);
        if (dto != null) return ResponseEntity.ok(dto);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleGroupResponseDTO> update(@PathVariable Long id, @Valid @RequestBody RoleGroupRequestDTO dto) {
        RoleGroupResponseDTO updated = roleGroupService.updateRoleGroup(id, dto);
        if (updated != null) return ResponseEntity.ok(updated);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleGroupService.deleteRoleGroup(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleGroupResponseDTO>> getAll() {
        return ResponseEntity.ok(roleGroupService.getAllRoleGroups());
    }

    @PostMapping("/{id}/assign-permissions")
    public ResponseEntity<?> assignPermissionsToRoleGroup(
            @PathVariable Long id,
            @RequestBody AssignPermissionsRequestDTO request) {
        roleGroupService.assignPermissionsToRoleGroup(id, request.getPermissionIds());
        return ResponseEntity.ok("Permissions assigned successfully");
    }

    @PostMapping("/{id}/add-permission/{permissionId}")
    public ResponseEntity<?> addPermissionToRoleGroup(
            @PathVariable Long id,
            @PathVariable Long permissionId) {
        roleGroupService.addPermissionToRoleGroup(id, permissionId);
        return ResponseEntity.ok("Permission added successfully");
    }

    @PostMapping("/{id}/remove-permission/{permissionId}")
    public ResponseEntity<?> removePermissionFromRoleGroup(
            @PathVariable Long id,
            @PathVariable Long permissionId) {
        roleGroupService.removePermissionFromRoleGroup(id, permissionId);
        return ResponseEntity.ok("Permission removed successfully");
    }
}

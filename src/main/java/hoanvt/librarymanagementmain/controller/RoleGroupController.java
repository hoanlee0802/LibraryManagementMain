package hoanvt.librarymanagementmain.controller;

import hoanvt.librarymanagementmain.dto.RoleGroupRequestDTO;
import hoanvt.librarymanagementmain.dto.RoleGroupResponseDTO;
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

    @PostMapping
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

    @GetMapping
    public ResponseEntity<List<RoleGroupResponseDTO>> getAll() {
        return ResponseEntity.ok(roleGroupService.getAllRoleGroups());
    }
}

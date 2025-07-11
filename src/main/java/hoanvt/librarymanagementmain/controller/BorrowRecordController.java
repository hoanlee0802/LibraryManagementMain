package hoanvt.librarymanagementmain.controller;

import hoanvt.librarymanagementmain.dto.request.BorrowRecordRequestDTO;
import hoanvt.librarymanagementmain.dto.response.BorrowRecordResponseDTO;
import hoanvt.librarymanagementmain.service.BorrowRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    // Replace this with your actual way to get current user's ID from JWT/session
    private Long getCurrentUserId(Principal principal) {
        // Example: return userService.findByUsername(principal.getName()).getId();
        return Long.valueOf(principal.getName()); // For demo only!
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_VIEW_BORROW')")
    public ResponseEntity<List<BorrowRecordResponseDTO>> getMyBorrows(Principal principal) {
        Long userId = getCurrentUserId(principal);
        return ResponseEntity.ok(borrowRecordService.getMyBorrowRecords(userId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE_BORROW')")
    public ResponseEntity<BorrowRecordResponseDTO> create(@Valid @RequestBody BorrowRecordRequestDTO dto, Principal principal) {
        dto.setUserId(getCurrentUserId(principal)); // Ensure user is set
        return ResponseEntity.ok(borrowRecordService.createBorrowRecord(dto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_VIEW_BORROW')")
    public ResponseEntity<BorrowRecordResponseDTO> getById(@PathVariable Long id, Principal principal) {
        BorrowRecordResponseDTO br = borrowRecordService.getBorrowRecordById(id, getCurrentUserId(principal));
        if (br != null) return ResponseEntity.ok(br);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_BORROW')")
    public ResponseEntity<BorrowRecordResponseDTO> update(@PathVariable Long id, @Valid @RequestBody BorrowRecordRequestDTO dto, Principal principal) {
        BorrowRecordResponseDTO br = borrowRecordService.updateBorrowRecord(id, dto, getCurrentUserId(principal));
        if (br != null) return ResponseEntity.ok(br);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_BORROW')")
    public ResponseEntity<Void> delete(@PathVariable Long id, Principal principal) {
        borrowRecordService.deleteBorrowRecord(id, getCurrentUserId(principal));
        return ResponseEntity.noContent().build();
    }
}

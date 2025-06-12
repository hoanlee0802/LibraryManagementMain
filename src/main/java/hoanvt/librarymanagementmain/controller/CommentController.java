package hoanvt.librarymanagementmain.controller;

import hoanvt.librarymanagementmain.dto.request.CommentRequestDTO;
import hoanvt.librarymanagementmain.dto.response.CommentResponseDTO;
import hoanvt.librarymanagementmain.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Get all comments of a post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    // Create comment
    @PostMapping
    public ResponseEntity<CommentResponseDTO> create(@Valid @RequestBody CommentRequestDTO dto) {
        return ResponseEntity.ok(commentService.createComment(dto));
    }

    // Get single comment
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> getById(@PathVariable Long id) {
        CommentResponseDTO dto = commentService.getCommentById(id);
        if (dto != null) return ResponseEntity.ok(dto);
        return ResponseEntity.notFound().build();
    }

    // Update comment (only content can be updated)
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CommentRequestDTO dto) {
        CommentResponseDTO updated = commentService.updateComment(id, dto);
        if (updated != null) return ResponseEntity.ok(updated);
        return ResponseEntity.notFound().build();
    }

    // Delete comment (only your own comment)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam Long userId) {
        commentService.deleteComment(id, userId);
        return ResponseEntity.noContent().build();
    }
}


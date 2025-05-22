package hoanvt.librarymanagementmain.controller;

import hoanvt.librarymanagementmain.dto.PostRequestDTO;
import hoanvt.librarymanagementmain.dto.PostResponseDTO;
import hoanvt.librarymanagementmain.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    private Long getCurrentUserId(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("Unauthorized: Principal is null");
        }
        return Long.valueOf(principal.getName());
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_VIEW_POST')")
    public ResponseEntity<List<PostResponseDTO>> getAll() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CREATE_POST')")
    public ResponseEntity<PostResponseDTO> create(@Valid @RequestBody PostRequestDTO dto, Principal principal) {
        return ResponseEntity.ok(postService.createPost(dto, getCurrentUserId(principal)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_VIEW_POST')")
    public ResponseEntity<PostResponseDTO> getById(@PathVariable Long id) {
        PostResponseDTO post = postService.getPostById(id);
        if (post != null) return ResponseEntity.ok(post);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_UPDATE_POST')")
    public ResponseEntity<PostResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PostRequestDTO dto, Principal principal) {
        PostResponseDTO post = postService.updatePost(id, dto, getCurrentUserId(principal));
        if (post != null) return ResponseEntity.ok(post);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DELETE_POST')")
    public ResponseEntity<Void> delete(@PathVariable Long id, Principal principal) {
        postService.deletePost(id, getCurrentUserId(principal));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/like")
    @PreAuthorize("hasRole('ROLE_VIEW_POST')")
    public ResponseEntity<PostResponseDTO> like(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(postService.likePost(id, getCurrentUserId(principal)));
    }

    @PostMapping("/{id}/dislike")
    @PreAuthorize("hasRole('ROLE_VIEW_POST')")
    public ResponseEntity<PostResponseDTO> dislike(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(postService.dislikePost(id, getCurrentUserId(principal)));
    }
}

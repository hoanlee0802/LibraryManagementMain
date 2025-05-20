package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.CommentRequestDTO;
import hoanvt.librarymanagementmain.dto.CommentResponseDTO;

import java.util.List;

public interface CommentService {
    List<CommentResponseDTO> getCommentsByPost(Long postId);
    CommentResponseDTO createComment(CommentRequestDTO dto);
    CommentResponseDTO getCommentById(Long id);
    CommentResponseDTO updateComment(Long id, CommentRequestDTO dto);
    void deleteComment(Long id, Long userId); // only allow delete own comment
}

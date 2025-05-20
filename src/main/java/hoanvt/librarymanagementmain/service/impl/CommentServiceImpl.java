package hoanvt.librarymanagementmain.service.impl;

import hoanvt.librarymanagementmain.dto.CommentRequestDTO;
import hoanvt.librarymanagementmain.dto.CommentResponseDTO;
import hoanvt.librarymanagementmain.entity.Comment;
import hoanvt.librarymanagementmain.entity.Post;
import hoanvt.librarymanagementmain.entity.User;
import hoanvt.librarymanagementmain.repository.CommentRepository;
import hoanvt.librarymanagementmain.repository.PostRepository;
import hoanvt.librarymanagementmain.repository.UserRepository;
import hoanvt.librarymanagementmain.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private CommentResponseDTO toResponseDTO(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setPostId(comment.getPost().getId());
        dto.setUserId(comment.getUser().getId());
        dto.setUsername(comment.getUser().getUsername());
        return dto;
    }

    @Override
    public List<CommentResponseDTO> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId)
                .stream().map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponseDTO createComment(CommentRequestDTO dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setPost(post);
        comment.setUser(user);
        comment = commentRepository.save(comment);
        return toResponseDTO(comment);
    }

    @Override
    public CommentResponseDTO getCommentById(Long id) {
        Optional<Comment> opt = commentRepository.findById(id);
        return opt.map(this::toResponseDTO).orElse(null);
    }

    @Override
    public CommentResponseDTO updateComment(Long id, CommentRequestDTO dto) {
        Optional<Comment> opt = commentRepository.findById(id);
        if (opt.isPresent()) {
            Comment comment = opt.get();
            // Only update content
            comment.setContent(dto.getContent());
            comment = commentRepository.save(comment);
            return toResponseDTO(comment);
        }
        return null;
    }

    @Override
    public void deleteComment(Long id, Long userId) {
        commentRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(userId))
                .ifPresent(commentRepository::delete);
    }
}

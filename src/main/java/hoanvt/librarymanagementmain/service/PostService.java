package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.request.PostRequestDTO;
import hoanvt.librarymanagementmain.dto.response.PostResponseDTO;

import java.util.List;

public interface PostService {
    List<PostResponseDTO> getAllPosts();
    PostResponseDTO createPost(PostRequestDTO dto, Long userId);
    PostResponseDTO getPostById(Long id);
    PostResponseDTO updatePost(Long id, PostRequestDTO dto, Long userId);
    void deletePost(Long id, Long userId);
    PostResponseDTO likePost(Long postId, Long userId);
    PostResponseDTO dislikePost(Long postId, Long userId);
}


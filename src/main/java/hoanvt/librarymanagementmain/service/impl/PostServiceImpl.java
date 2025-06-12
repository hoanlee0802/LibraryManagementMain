package hoanvt.librarymanagementmain.service.impl;

import hoanvt.librarymanagementmain.dto.request.PostRequestDTO;
import hoanvt.librarymanagementmain.dto.response.PostResponseDTO;
import hoanvt.librarymanagementmain.entity.Book;
import hoanvt.librarymanagementmain.entity.Post;
import hoanvt.librarymanagementmain.entity.User;
import hoanvt.librarymanagementmain.repository.BookRepository;
import hoanvt.librarymanagementmain.repository.PostRepository;
import hoanvt.librarymanagementmain.repository.UserRepository;
import hoanvt.librarymanagementmain.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    private PostResponseDTO toResponseDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUserId(post.getUser() != null ? post.getUser().getId() : null);
        dto.setUsername(post.getUser() != null ? post.getUser().getUsername() : null);
        dto.setBookId(post.getBook() != null ? post.getBook().getId() : null);
        dto.setBookTitle(post.getBook() != null ? post.getBook().getTitle() : null);

        if (post.getLikedByUsers() != null) {
            Set<Long> ids = post.getLikedByUsers().stream().map(User::getId).collect(Collectors.toSet());
            dto.setLikedUserIds(ids);
            dto.setLikeCount(ids.size());
        } else {
            dto.setLikedUserIds(Collections.emptySet());
            dto.setLikeCount(0);
        }
        return dto;
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public PostResponseDTO createPost(PostRequestDTO dto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Book book = dto.getBookId() != null ? bookRepository.findById(dto.getBookId()).orElse(null) : null;
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);
        post.setBook(book);
        post = postRepository.save(post);
        return toResponseDTO(post);
    }

    @Override
    public PostResponseDTO getPostById(Long id) {
        return postRepository.findById(id).map(this::toResponseDTO).orElse(null);
    }

    @Override
    public PostResponseDTO updatePost(Long id, PostRequestDTO dto, Long userId) {
        Optional<Post> opt = postRepository.findById(id);
        if (opt.isPresent()) {
            Post post = opt.get();
            if (!post.getUser().getId().equals(userId)) throw new RuntimeException("Not owner");
            post.setTitle(dto.getTitle());
            post.setContent(dto.getContent());
            if (dto.getBookId() != null) {
                Book book = bookRepository.findById(dto.getBookId()).orElse(null);
                post.setBook(book);
            }
            post = postRepository.save(post);
            return toResponseDTO(post);
        }
        return null;
    }

    @Override
    public void deletePost(Long id, Long userId) {
        postRepository.findById(id)
                .filter(p -> p.getUser().getId().equals(userId))
                .ifPresent(postRepository::delete);
    }

    @Override
    public PostResponseDTO likePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        post.getLikedByUsers().add(user);
        post = postRepository.save(post);
        return toResponseDTO(post);
    }

    @Override
    public PostResponseDTO dislikePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        post.getLikedByUsers().remove(user);
        post = postRepository.save(post);
        return toResponseDTO(post);
    }
}

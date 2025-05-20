package hoanvt.librarymanagementmain.service.impl;

import hoanvt.librarymanagementmain.dto.CategoryStatisticsDTO;
import hoanvt.librarymanagementmain.dto.TopLikedPostDTO;
import hoanvt.librarymanagementmain.repository.CategoryRepository;
import hoanvt.librarymanagementmain.repository.PostRepository;
import hoanvt.librarymanagementmain.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<CategoryStatisticsDTO> getCategoryStatistics() {
        // Assumes Category has getBooks() for the ManyToMany relation
        return categoryRepository.findAll().stream()
                .map(category -> {
                    CategoryStatisticsDTO dto = new CategoryStatisticsDTO();
                    dto.setCategoryCode(category.getCategoryCode());
                    dto.setCategoryName(category.getCategoryName());
                    dto.setBookCount(category.getBooks() != null ? category.getBooks().size() : 0);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TopLikedPostDTO> getTop5LikedPosts() {
        return postRepository.findAll().stream()
                .sorted((p1, p2) -> Integer.compare(
                        p2.getLikedByUsers() != null ? p2.getLikedByUsers().size() : 0,
                        p1.getLikedByUsers() != null ? p1.getLikedByUsers().size() : 0
                ))
                .limit(5)
                .map(post -> {
                    TopLikedPostDTO dto = new TopLikedPostDTO();
                    dto.setId(post.getId());
                    dto.setTitle(post.getTitle());
                    dto.setContent(post.getContent());
                    dto.setLikeCount(post.getLikedByUsers() != null ? post.getLikedByUsers().size() : 0);
                    dto.setUsername(post.getUser() != null ? post.getUser().getUsername() : null);
                    dto.setCreatedAt(post.getCreatedAt());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

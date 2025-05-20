package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.CategoryStatisticsDTO;
import hoanvt.librarymanagementmain.dto.TopLikedPostDTO;

import java.util.List;

public interface DashboardService {
    List<CategoryStatisticsDTO> getCategoryStatistics();
    List<TopLikedPostDTO> getTop5LikedPosts();
}

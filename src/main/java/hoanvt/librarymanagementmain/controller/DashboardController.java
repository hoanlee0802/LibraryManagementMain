package hoanvt.librarymanagementmain.controller;

import hoanvt.librarymanagementmain.dto.CategoryStatisticsDTO;
import hoanvt.librarymanagementmain.dto.TopLikedPostDTO;
import hoanvt.librarymanagementmain.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/category-statistics")
    public ResponseEntity<List<CategoryStatisticsDTO>> getCategoryStatistics() {
        return ResponseEntity.ok(dashboardService.getCategoryStatistics());
    }

    @GetMapping("/top-liked-posts")
    public ResponseEntity<List<TopLikedPostDTO>> getTop5LikedPosts() {
        return ResponseEntity.ok(dashboardService.getTop5LikedPosts());
    }
}

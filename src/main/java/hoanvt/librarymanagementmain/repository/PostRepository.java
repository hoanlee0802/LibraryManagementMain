package hoanvt.librarymanagementmain.repository;

import hoanvt.librarymanagementmain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
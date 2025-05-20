package hoanvt.librarymanagementmain.repository;

import hoanvt.librarymanagementmain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
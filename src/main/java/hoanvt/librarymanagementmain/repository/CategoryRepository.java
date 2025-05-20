package hoanvt.librarymanagementmain.repository;

import hoanvt.librarymanagementmain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryCode(String categoryCode);
    Optional<Category> findByCategoryName(String categoryName);
}
package hoanvt.librarymanagementmain.service;

import hoanvt.librarymanagementmain.dto.request.CategoryRequestDTO;
import hoanvt.librarymanagementmain.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO createCategory(CategoryRequestDTO dto);
    CategoryResponseDTO getCategoryById(Long id);
    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto);
    void deleteCategory(Long id);
}


package hoanvt.librarymanagementmain.service.impl;

import hoanvt.librarymanagementmain.dto.request.CategoryRequestDTO;
import hoanvt.librarymanagementmain.dto.response.CategoryResponseDTO;
import hoanvt.librarymanagementmain.entity.Category;
import hoanvt.librarymanagementmain.repository.CategoryRepository;
import hoanvt.librarymanagementmain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryResponseDTO toResponseDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setCategoryCode(category.getCategoryCode());
        dto.setCategoryName(category.getCategoryName());
        return dto;
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        if (categoryRepository.findByCategoryCode(dto.getCategoryCode()).isPresent()) {
            throw new RuntimeException("Category code already exists");
        }
        Category category = new Category();
        category.setCategoryCode(dto.getCategoryCode());
        category.setCategoryName(dto.getCategoryName());
        return toResponseDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        Optional<Category> catOpt = categoryRepository.findById(id);
        return catOpt.map(this::toResponseDTO).orElse(null);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto) {
        Optional<Category> catOpt = categoryRepository.findById(id);
        if (catOpt.isPresent()) {
            Category category = catOpt.get();
            category.setCategoryCode(dto.getCategoryCode());
            category.setCategoryName(dto.getCategoryName());
            return toResponseDTO(categoryRepository.save(category));
        }
        return null;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}

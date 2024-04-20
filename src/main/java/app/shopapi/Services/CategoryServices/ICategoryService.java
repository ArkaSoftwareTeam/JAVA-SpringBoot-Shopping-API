package app.shopapi.Services.CategoryServices;

import app.shopapi.DTOs.CategoryDTO;
import app.shopapi.DTOs.CategoryResponseDTO;
import app.shopapi.Models.Category;

public interface ICategoryService {
    CategoryDTO createCategory(Category category);

    CategoryResponseDTO getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO updateCategory(Category category, Long categoryId);

    String deleteCategory(Long categoryId);
}

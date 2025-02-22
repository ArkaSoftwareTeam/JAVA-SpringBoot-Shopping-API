package app.shopapi.Controllers;


import app.shopapi.Configurations.AppConstants;
import app.shopapi.DTOs.CategoryDTO;
import app.shopapi.DTOs.CategoryResponseDTO;
import app.shopapi.Models.Category;
import app.shopapi.Services.CategoryServices.ICategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Spring Boot Web Api")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/admin/category")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody Category category) {
        CategoryDTO savedCategoryDTO = categoryService.createCategory(category);

        return new ResponseEntity<CategoryDTO>(savedCategoryDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponseDTO> getCategories(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {

        CategoryResponseDTO categoryResponse = categoryService.getCategories(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<CategoryResponseDTO>(categoryResponse, HttpStatus.FOUND);
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody Category category,
                                                      @PathVariable Long categoryId) {
        CategoryDTO categoryDTO = categoryService.updateCategory(category, categoryId);

        return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        String status = categoryService.deleteCategory(categoryId);

        return new ResponseEntity<String>(status, HttpStatus.OK);
    }
}

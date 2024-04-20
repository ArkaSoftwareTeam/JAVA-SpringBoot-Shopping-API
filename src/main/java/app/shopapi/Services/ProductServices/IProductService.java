package app.shopapi.Services.ProductServices;

import app.shopapi.DTOs.ProductDTO;
import app.shopapi.DTOs.ProductResponseDTO;
import app.shopapi.Models.Product;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface IProductService {
    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponseDTO getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductResponseDTO searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy,
                                     String sortOrder);

    ProductDTO updateProduct(Long productId, Product product);

    ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException;

    ProductResponseDTO searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy,
                                           String sortOrder);

    String deleteProduct(Long productId);
}

package app.shopapi.Repositories;

import app.shopapi.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product , Long> {
    Page<Product> findByProductNameLike(String keyword, Pageable pageDetails);
}

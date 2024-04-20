package app.shopapi.Repositories;

import app.shopapi.Models.Cart;
import app.shopapi.Models.CartItem;
import app.shopapi.Models.Product;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICartItemRepository extends JpaRepository<CartItem , Long> {
    @Query("SELECT ci.product FROM CartItem ci WHERE ci.product.productId = ?1")
    Product findProductById(Long productId);

	@Query("SELECT ci.cart FROM CartItem ci WHERE ci.product.productId = ?1")
    List<Cart> findCartsByProductId(Long productId);

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = ?1 AND ci.product.productId = ?2")
    CartItem findCartItemByProductIdAndCartId(Long cartId, Long productId);

	@Query("SELECT ci.cart FROM CartItem ci WHERE ci.cart.user.email = ?1 AND ci.cart.id = ?2")
	Cart findCartByEmailAndCartId(String email, Integer cartId);

    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart.cartId = ?1 AND ci.product.productId = ?2")
    void deleteCartItemByProductIdAndCartId(Long productId, Long cartId);
}

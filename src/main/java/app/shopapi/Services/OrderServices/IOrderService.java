package app.shopapi.Services.OrderServices;

import app.shopapi.DTOs.OrderDTO;
import app.shopapi.DTOs.OrderResponseDTO;
import java.util.List;

public interface IOrderService {
    OrderDTO placeOrder(String emailId, Long cartId, String paymentMethod);

    OrderDTO getOrder(String emailId, Long orderId);

    List<OrderDTO> getOrdersByUser(String emailId);

    OrderResponseDTO getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    OrderDTO updateOrder(String emailId, Long orderId, String orderStatus);
}

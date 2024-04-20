package app.shopapi.Services.OrderServices;


import app.shopapi.DTOs.OrderDTO;
import app.shopapi.DTOs.OrderItemDTO;
import app.shopapi.DTOs.OrderResponseDTO;
import app.shopapi.Exceptions.APIException;
import app.shopapi.Exceptions.ResourceNotFoundException;
import app.shopapi.Models.*;
import app.shopapi.Repositories.*;
import app.shopapi.Services.AccountingServices.IUserService;
import app.shopapi.Services.CartServices.ICartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderService implements IOrderService{
    public final IUserRepository userRepo;
    public final ICartRepository cartRepo;
    public final IOrderRepository orderRepo;
    private final IPaymentRepository paymentRepo;
    public final IOrderItemRepository orderItemRepo;
    public final ICartItemRepository cartItemRepo;
    public final IUserService userService;
    public final ICartService cartService;
    public final ModelMapper modelMapper;

    @Autowired
    public OrderService(IUserRepository userRepo, ICartRepository cartRepo, IOrderRepository orderRepo, IPaymentRepository paymentRepo, IOrderItemRepository orderItemRepo, ICartItemRepository cartItemRepo, IUserService userService, ICartService cartService, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
        this.orderRepo = orderRepo;
        this.paymentRepo = paymentRepo;
        this.orderItemRepo = orderItemRepo;
        this.cartItemRepo = cartItemRepo;
        this.userService = userService;
        this.cartService = cartService;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDTO placeOrder(String emailId, Long cartId, String paymentMethod) {
        Cart cart = cartRepo.findCartByEmailAndCartId(emailId, cartId);

        if (cart == null) {
            throw new ResourceNotFoundException("Cart", "cartId", cartId);
        }

        Order order = new Order();

        order.setEmail(emailId);
        order.setOrderDate(LocalDate.now());

        order.setTotalAmount(cart.getTotalPrice());
        order.setOrderStatus("Order Accepted !");

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod(paymentMethod);

        payment = paymentRepo.save(payment);

        order.setPayment(payment);

        Order savedOrder = orderRepo.save(order);

        List<CartItem> cartItems = cart.getCartItems();

        if (cartItems.size() == 0) {
            throw new APIException("Cart is empty");
        }

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();

            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setDiscount(cartItem.getDiscount());
            orderItem.setOrderedProductPrice(cartItem.getProductPrice());
            orderItem.setOrder(savedOrder);

            orderItems.add(orderItem);
        }

        orderItems = orderItemRepo.saveAll(orderItems);

        cart.getCartItems().forEach(item -> {
            int quantity = item.getQuantity();

            Product product = item.getProduct();

            cartService.deleteProductFromCart(cartId, item.getProduct().getProductId());

            product.setQuantity(product.getQuantity() - quantity);
        });

        OrderDTO orderDTO = modelMapper.map(savedOrder, OrderDTO.class);

        orderItems.forEach(item -> orderDTO.getOrderItems().add(modelMapper.map(item, OrderItemDTO.class)));

        return orderDTO;
    }

    @Override
    public OrderDTO getOrder(String emailId, Long orderId) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersByUser(String emailId) {
        return null;
    }

    @Override
    public OrderResponseDTO getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }

    @Override
    public OrderDTO updateOrder(String emailId, Long orderId, String orderStatus) {
        return null;
    }
}

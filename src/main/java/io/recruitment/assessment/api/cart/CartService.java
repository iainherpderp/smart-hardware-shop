package io.recruitment.assessment.api.cart;

import io.recruitment.assessment.api.cart.exception.EmptyCartException;
import io.recruitment.assessment.api.order.OrderDto;
import io.recruitment.assessment.api.order.OrderService;
import io.recruitment.assessment.api.product.ProductRepository;
import io.recruitment.assessment.api.user.User;
import io.recruitment.assessment.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderService orderService;

    @Transactional
    CartDto retrieveCartByUser(User user) {
        Cart cart = cartRepository.findByUserNotNull(user);
        return toCartDto(cart);
    }

    @Transactional
    void addToCart(User user, long productId) {
        Optional<Cart> optional = cartRepository.findByUser(user);

        Cart cart;
        if (optional.isPresent()) {
            cart = optional.get();
        } else {
            cart = new Cart();
            cart.setUser(user);
        }
        cart.getProducts().add(productRepository.findByIdNotNull(productId));
        cartRepository.save(cart);
    }

    @Transactional
    void removeFromCart(User user, long productId) {
        Optional<Cart> optional = cartRepository.findByUser(user);

        Cart cart;
        if (optional.isPresent()) {
            cart = optional.get();
        } else {
            cart = new Cart();
            cart.setUser(user);
        }
        cart.getProducts().remove(productRepository.findByIdNotNull(productId));
        cartRepository.save(cart);
    }

    @Transactional
    void emptyCart(User user) {
        Optional<Cart> optional = cartRepository.findByUser(user);
        Cart cart;
        if (optional.isPresent()) {
            cart = optional.get();
        } else {
            cart = new Cart();
            cart.setUser(user);
        }
        cart.getProducts().clear();
        cartRepository.save(cart);
    }

    @Transactional
    OrderDto checkout(User user) {
        OrderDto order = placeOrder(user);
        emptyCart(user);
        return order;
    }

    @Transactional
    OrderDto placeOrder(User user) {
        Optional<Cart> optional = cartRepository.findByUser(user);
        if (optional.isEmpty()) {
            throw new EmptyCartException("User's cart is empty");
        }
        Cart cart = optional.get();
        if (cart.getProducts().isEmpty()) {
            throw new EmptyCartException("User's cart is empty");
        }
        return orderService.placeOrder(cart);
    }

    CartDto toCartDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUser().getId());
        dto.setProducts(cart.getProducts());
        dto.setTotalPrice(cart.getTotalPrice());
        return dto;
    }

}

package io.recruitment.assessment.api.cart;

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

    @Transactional
    Cart retrieveCartByUser(User user) {
        return cartRepository.findByUserNotNull(user);
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

}

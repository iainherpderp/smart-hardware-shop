package io.recruitment.assessment.api.cart;

import io.recruitment.assessment.api.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping
    public ResponseEntity<Cart> getCart(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.retrieveCartByUser(user));
    }

    @DeleteMapping
    public ResponseEntity<Void> emptyCart(@AuthenticationPrincipal User user) {
        service.emptyCart(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/product/{productId}")
    public ResponseEntity<Void> addToCart(@AuthenticationPrincipal User user, @PathVariable long productId) {
        service.addToCart(user, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/product/{productId}")
    public ResponseEntity<Void> removeFromCart(@AuthenticationPrincipal User user, @PathVariable long productId) {
        service.removeFromCart(user, productId);
        return ResponseEntity.ok().build();
    }


}

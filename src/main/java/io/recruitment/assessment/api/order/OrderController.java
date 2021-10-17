package io.recruitment.assessment.api.order;

import io.recruitment.assessment.api.exception.UnauthorisedAccessException;
import io.recruitment.assessment.api.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<Collection<OrderDto>> getOrdersForLoggedInUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.findAllOrdersForCustomer(user));
    }

    @GetMapping("/open")
    public ResponseEntity<Collection<OrderDto>> getOpenOrdersForLoggedInUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.findAllOpenOrdersForCustomer(user));
    }

    @GetMapping("/complete")
    public ResponseEntity<Collection<OrderDto>> getCompletedOrdersForLoggedInUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.findAllCompletedOrdersForCustomer(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@AuthenticationPrincipal User user, @PathVariable long id) throws UnauthorisedAccessException {
        return ResponseEntity.ok(service.findById(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@AuthenticationPrincipal User user, @PathVariable long id) throws UnauthorisedAccessException {
        service.cancelOrder(user, id);
        return ResponseEntity.ok().build();
    }
}

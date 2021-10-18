package io.recruitment.assessment.api.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private AdminOrderService service;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> completeOrder(@PathVariable long id) {
        service.completeOrder(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable long id) {
        service.cancelOrder(id);
        return ResponseEntity.ok().build();
    }

}

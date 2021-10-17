package io.recruitment.assessment.api.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdminOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order findById(Long id) {
        return orderRepository.findByIdNotNull(id);
    }

    @Transactional
    public void completeOrder(long orderId) {
        Order order = orderRepository.findByIdNotNull(orderId);
        order.setCompleted(true);

        orderRepository.save(order);
    }

    @Transactional
    public void cancelOrder(long orderId) {
        orderRepository.deleteById(orderId);
    }

}
package io.recruitment.assessment.api.order;

import io.recruitment.assessment.api.cart.Cart;
import io.recruitment.assessment.api.exception.UnauthorisedAccessException;
import io.recruitment.assessment.api.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public OrderDto findById(User user, Long orderId) throws UnauthorisedAccessException {
        verifyOwnership(user, orderId);
        Order order = orderRepository.findByIdNotNull(orderId);
        return toOrderDto(order);
    }

    @Transactional
    public Collection<OrderDto> findAllOrdersForCustomer(User user) {
        Collection<Order> orders = orderRepository.
                findByUser(user)
                .orElseGet(ArrayList::new);

        return orders.stream().map(this::toOrderDto).collect(toList());
    }

    @Transactional
    public Collection<OrderDto> findAllCompletedOrdersForCustomer(User user) {
        Collection<Order> allOrders = orderRepository.
                findByUser(user)
                .orElseGet(ArrayList::new);

        List<Order> openOrders = allOrders.stream()
                .filter(Order::isCompleted)
                .collect(toList());

        return openOrders.stream().map(this::toOrderDto).collect(toList());
    }

    @Transactional
    public Collection<OrderDto> findAllOpenOrdersForCustomer(User user) {
        Collection<Order> allOrders = orderRepository.
                findByUser(user)
                .orElseGet(ArrayList::new);

        List<Order> completedOrders = allOrders.stream()
                .filter(order -> !order.isCompleted())
                .collect(toList());

        return completedOrders.stream().map(this::toOrderDto).collect(toList());
    }

    @Transactional
    public OrderDto placeOrder(Cart cart) {
        Order order = toOrder(cart);
        Order savedOrder = orderRepository.save(order);
        return toOrderDto(savedOrder);
    }

    @Transactional
    public void completeOrder(User user, long orderId) throws UnauthorisedAccessException {
        verifyOwnership(user, orderId);
        Order order = orderRepository.findByIdNotNull(orderId);
        order.setCompleted(true);
        orderRepository.save(order);
    }

    @Transactional
    public void cancelOrder(User user, long orderId) throws UnauthorisedAccessException {
        verifyOwnership(user, orderId);
        orderRepository.deleteById(orderId);
    }

    private void verifyOwnership(User user, long orderId) throws UnauthorisedAccessException {
        Collection<Order> orders = orderRepository.findByUserNotNull(user);

        if (orders.stream().noneMatch(order -> order.getId().equals(orderId))) {
            throw new UnauthorisedAccessException("This order does not belong to logged in user");
        }
    }

    private Order toOrder(Cart cart) {
        Order order = new Order();
        order.setProducts(new ArrayList<>(cart.getProducts()));
        order.setUser(cart.getUser());
        order.setOrderDate(Timestamp.from(Instant.now()));
        return order;
    }

    private OrderDto toOrderDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setProducts(new ArrayList<>(order.getProducts()));
        dto.setUserId(order.getUser().getId());
        dto.setComplete(order.isCompleted());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalPrice(order.getTotalPrice());
        return dto;
    }

}

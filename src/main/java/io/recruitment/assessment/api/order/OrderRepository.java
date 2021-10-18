package io.recruitment.assessment.api.order;

import io.recruitment.assessment.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    default Order findByIdNotNull(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No order found with ID " +id));
    }

    default Collection<Order> findByUserNotNull(User user) {
        return findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("No order found for user " + user.getId()));
    }

    Optional<Collection<Order>> findByUser(User user);

}

package io.recruitment.assessment.api.cart;

import io.recruitment.assessment.api.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    default Cart findByUserNotNull(User user) {
        return findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("No cart found for user " + user.getId()));
    }

    Optional<Cart> findByUser(User user);

}

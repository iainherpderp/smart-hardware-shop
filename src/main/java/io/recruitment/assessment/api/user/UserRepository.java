package io.recruitment.assessment.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdNotNull(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Customer found with ID " + id));
    }

    Optional<User> findByEmail(String email);

}

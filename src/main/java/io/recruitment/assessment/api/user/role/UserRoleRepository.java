package io.recruitment.assessment.api.user.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<Collection<UserRole>> findByRole(Role role);

}

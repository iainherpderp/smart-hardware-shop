package io.recruitment.assessment.api;

import io.recruitment.assessment.api.user.role.Role;
import io.recruitment.assessment.api.user.User;
import io.recruitment.assessment.api.user.UserRepository;
import io.recruitment.assessment.api.user.role.UserRole;
import io.recruitment.assessment.api.user.role.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean isInitialised;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (isInitialised) {
            return;
        }

        User admin = createAdmin();
        User customer = createCustomer();

        createUserRole(Role.ROLE_ADMIN, admin);
        createUserRole(Role.ROLE_CUSTOMER, customer);

        isInitialised = true;
    }

    @Transactional
    User createAdmin() {
        return userRepository.findByEmail("admin@safetyhardwareshop.com")
                .orElseGet(() -> {
                    User user = new User();
                    user.setEmail("admin@safetyhardwareshop.com");
                    user.setPassword(passwordEncoder.encode("admin"));
                    user.setFirstName("Admin");
                    user.setSurname("User");

                    return userRepository.save(user);
                });
    }

    @Transactional
    User createCustomer() {
        return userRepository.findByEmail("customer@safetyhardwareshop.com")
                .orElseGet(() -> {
                    User user = new User();
                    user.setEmail("customer@safetyhardwareshop.com");
                    user.setPassword(passwordEncoder.encode("123456"));
                    user.setFirstName("test");
                    user.setSurname("customer");

                    return userRepository.save(user);
                });
    }

    @Transactional
    void createUserRole(Role role, User user) {
        Optional<Collection<UserRole>> userRoles = userRoleRepository.findByRole(role);

        if (userRoles.isEmpty()) {
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUserId(user.getId());

            userRoleRepository.save(userRole);
        }
    }

}

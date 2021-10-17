package io.recruitment.assessment.api.user;

import io.recruitment.assessment.api.user.exception.EmailExistsException;
import io.recruitment.assessment.api.user.exception.EmailNotFoundException;
import io.recruitment.assessment.api.user.role.Role;
import io.recruitment.assessment.api.user.role.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> { throw new EmailNotFoundException("Provided email does not exist");});
    }

    @Transactional
    User findById(long id) {
        return userRepository.findByIdNotNull(id);
    }

    @Transactional
    User registerCustomer(User user) throws EmailExistsException {
        return createUser(user, Role.ROLE_CUSTOMER);
    }

    @Transactional
    User registerAdmin(User user) throws EmailExistsException {
        return createUser(user, Role.ROLE_ADMIN);
    }

    @Transactional
    User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    void delete(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    private User createUser(User user, Role role) throws EmailExistsException {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailExistsException("The provided email address already exists " + user.getEmail());
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        userRoleService.createUserRole(savedUser.getId(), role);

        return savedUser;
    }

}

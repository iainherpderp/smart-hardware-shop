package io.recruitment.assessment.api.user.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRole createUserRole(Long userId, Role role) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRole(role);

        return userRoleRepository.save(userRole);
    }

}

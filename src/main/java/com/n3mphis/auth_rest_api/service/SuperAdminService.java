package com.n3mphis.auth_rest_api.service;

import com.n3mphis.auth_rest_api.model.Role;
import com.n3mphis.auth_rest_api.model.RoleName;
import com.n3mphis.auth_rest_api.model.User;
import com.n3mphis.auth_rest_api.repository.RoleRepository;
import com.n3mphis.auth_rest_api.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SuperAdminService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public SuperAdminService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User otorgarRoleAdmin(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        Role adminRole = roleRepository.findByNombre(RoleName.ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Rol 'ADMIN' no encontrado"));

        user.setRole(adminRole);

        return userRepository.save(user);
    }
}

package com.n3mphis.auth_rest_api.config;

import com.n3mphis.auth_rest_api.model.Role;
import com.n3mphis.auth_rest_api.model.RoleName;
import com.n3mphis.auth_rest_api.model.User;
import com.n3mphis.auth_rest_api.repository.RoleRepository;
import com.n3mphis.auth_rest_api.service.SuperAdminService;
import com.n3mphis.auth_rest_api.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Value("${app.superadmin.password}")
    private String superAdminPassword;

    public DataInitializer(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<Role> superAdminRoleOpt = roleRepository.findByNombre(RoleName.SUPER_ADMIN);
        if (superAdminRoleOpt.isEmpty()) {
            roleRepository.save(new Role(RoleName.SUPER_ADMIN));
        }

        Optional<Role> userRole = roleRepository.findByNombre(RoleName.USER);
        if (userRole.isEmpty()) {
            roleRepository.save(new Role(RoleName.USER));
        }

        if (userService.buscarUsuarioPorEmail("admin@miapp.com").isEmpty()) {
            User superAdminUser = new User("admin@miapp.com", superAdminPassword);
            Role superAdminRole = roleRepository.findByNombre(RoleName.SUPER_ADMIN).get();

            superAdminUser.setRole(superAdminRole);

            userService.registrarNuevoUsuario(superAdminUser);
            System.out.println("✅ Primer usuario SUPER ADMIN inicializado");
        }
    }
}

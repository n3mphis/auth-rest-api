package com.n3mphis.auth_rest_api.service;

import com.n3mphis.auth_rest_api.model.Role;
import com.n3mphis.auth_rest_api.model.RoleName;
import com.n3mphis.auth_rest_api.model.User;
import com.n3mphis.auth_rest_api.repository.RoleRepository;
import com.n3mphis.auth_rest_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registrarNuevoUsuario(User user) {
        String passwordEncriptada = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncriptada);

        if (user.getRole() == null) {
            Role rolUsuario = roleRepository.findByName(RoleName.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Rol USER no encontrado"));
            user.setRole(rolUsuario);
        }
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con mail: " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

    public List<User> encontrarTodosLosUsuarios() {
        return userRepository.findAll();
    }

    public Optional<User> buscarUsuarioPorEmail(String email) {
        return userRepository.findByEmail(email);
    }


}

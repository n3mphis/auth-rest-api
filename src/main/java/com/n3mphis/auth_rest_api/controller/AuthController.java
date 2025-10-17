package com.n3mphis.auth_rest_api.controller;

import com.n3mphis.auth_rest_api.dto.UserResponseDTO;
import com.n3mphis.auth_rest_api.dto.UsuarioDTO;
import com.n3mphis.auth_rest_api.model.User;
import com.n3mphis.auth_rest_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDTO dto) {
        User usuarioNuevo = new  User(
                dto.email(),
                dto.password()
        );

        userService.registrarNuevoUsuario(usuarioNuevo);

        return ResponseEntity.created(null).build();
    }

    @GetMapping("/mostrarUsuarios")
    public ResponseEntity<List<UserResponseDTO>> mostrarTodosLosUsuarios() {
        List<UserResponseDTO> users = userService.encontrarTodosLosUsuarios();

        return ResponseEntity.ok(users);
    }
}

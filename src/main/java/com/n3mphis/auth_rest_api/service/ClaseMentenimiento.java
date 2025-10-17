package com.n3mphis.auth_rest_api.service;

import com.n3mphis.auth_rest_api.model.User;
import org.springframework.stereotype.Service;

@Service
public class ClaseMentenimiento {

    private final SuperAdminService superAdminService;

    public ClaseMentenimiento(SuperAdminService superAdminService) {
        this.superAdminService = superAdminService;
    }

    public void promoverUsuario(String email) {
        User usuarioPromovido = superAdminService.otorgarRoleAdmin(email);
        System.out.println("Usuario: " + usuarioPromovido.getEmail() + " promovido.");
    }
}

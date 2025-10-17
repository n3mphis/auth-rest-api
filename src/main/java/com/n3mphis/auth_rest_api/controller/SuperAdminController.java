package com.n3mphis.auth_rest_api.controller;

import com.n3mphis.auth_rest_api.service.SuperAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/superAdmin")
public class SuperAdminController {
    private final SuperAdminService superAdminService;

    public SuperAdminController(SuperAdminService superAdminService) {
        this.superAdminService = superAdminService;
    }

    @PutMapping("/otorgarAdmin/{email}")
    @PreAuthorize("hasROle('SUPER_ADMIN')")
    public ResponseEntity<?> otorgarAdmin(@PathVariable String email) {
        superAdminService.otorgarRoleAdmin(email);

        return ResponseEntity.noContent().build();
    }
}

package com.n3mphis.auth_rest_api.dto;

import com.n3mphis.auth_rest_api.model.RoleName;

public record UserResponseDTO(
        String email,
        Long id,
        RoleName role
) {
}

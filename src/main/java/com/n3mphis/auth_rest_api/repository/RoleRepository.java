package com.n3mphis.auth_rest_api.repository;

import com.n3mphis.auth_rest_api.model.Role;
import com.n3mphis.auth_rest_api.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByNombre(RoleName nombre);
}

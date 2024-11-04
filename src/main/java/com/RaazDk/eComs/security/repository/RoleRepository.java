package com.RaazDk.eComs.security.repository;

import com.RaazDk.eComs.security.model.AppRole;
import com.RaazDk.eComs.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(AppRole appRole);
}

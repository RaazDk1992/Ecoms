package com.RaazDk.eComs.repository;

import com.RaazDk.eComs.models.AppRole;
import com.RaazDk.eComs.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(AppRole appRole);
}

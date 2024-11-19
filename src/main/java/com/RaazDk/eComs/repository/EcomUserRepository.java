package com.RaazDk.eComs.repository;

import com.RaazDk.eComs.models.EcomUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EcomUserRepository  extends JpaRepository<EcomUser,Long> {

    Optional<EcomUser> findByUserName(String username);

    Optional<EcomUser> findByEmail(String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(@NotBlank @Size(min = 8, max = 50) String email);
}

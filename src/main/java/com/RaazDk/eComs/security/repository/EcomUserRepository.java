package com.RaazDk.eComs.security.repository;

import com.RaazDk.eComs.security.model.EcomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EcomUserRepository  extends JpaRepository<EcomUser,Long> {

    Optional<EcomUser> findByUserName(String username);
}

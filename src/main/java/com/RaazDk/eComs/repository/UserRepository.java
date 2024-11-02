package com.RaazDk.eComs.repository;

import com.RaazDk.eComs.models.EcomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<EcomUser,Long> {

    Optional<EcomUser> findByUserName(String  userName);
}

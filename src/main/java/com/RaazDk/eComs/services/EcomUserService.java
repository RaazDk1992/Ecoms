package com.RaazDk.eComs.services;

import com.RaazDk.eComs.models.EcomUser;
import com.RaazDk.eComs.security.requests.SignupRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface EcomUserService {
    public ResponseEntity<?> saveUser(SignupRequest request);
    public ResponseEntity<?> updatePassword(String oldPassword, String newPassword);

    EcomUser findByUsername(String username);

    public ResponseEntity<?> registerUser(EcomUser ecomUser);

    Optional<EcomUser> findByEmail(String email);
}

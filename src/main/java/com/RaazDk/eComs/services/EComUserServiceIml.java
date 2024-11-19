package com.RaazDk.eComs.services;

import com.RaazDk.eComs.models.AppRole;
import com.RaazDk.eComs.models.EcomUser;
import com.RaazDk.eComs.models.Role;
import com.RaazDk.eComs.repository.EcomUserRepository;
import com.RaazDk.eComs.repository.RoleRepository;
import com.RaazDk.eComs.security.requests.SignupRequest;
import com.RaazDk.eComs.security.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class EComUserServiceIml implements EcomUserService {

    @Autowired
    private EcomUserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> saveUser(SignupRequest request) {
        // Check for existing username and email

        if (userRepository.existsByUserName(request.getUserName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username already in use."));
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email already in use."));
        }

        // Determine role for the new request

        Role role;
        Set<String> strRoles = request.getRole();
        EcomUser user = new EcomUser();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword())); // Encrypt password here

        user.setAccountNonLocked(request.isAccountNonLocked());
        user.setAccountNonExpired(request.isAccountNonExpired());
        user.setCredentialsNonExpired(request.isCredentialsNonExpired());
        user.setEnabled(request.isEnabled());
        user.setCredentialsExpiryDate(request.getCredentialsExpiryDate());
        user.setAccountExpiryDate(request.getAccountExpiryDate());
        user.set2faEnabled(request.is2faEnabled());

        if (strRoles == null) {  // Default to ROLE_USER if no role is provided
            role = roleRepository.findByRoleName(AppRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role not found"));
        } else {
            // Check if the request requested an admin role or request role
            String roleName = strRoles.iterator().next();
            if ("ROLE_ADMIN".equals(roleName)) {
                role = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role not found!"));
            } else {
                role = roleRepository.findByRoleName(AppRole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role not found"));
            }
        }

        // Assign role and encode password
        user.setRole(role);

        // Save request to repository
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully."));
    }

    @Override
    public ResponseEntity<?> updatePassword(String oldPassword, String newPassword) {
        return null;
    }

    @Override
    public EcomUser findByUsername(String username) {
        return null;
    }

    @Override
    public ResponseEntity<?> registerUser(EcomUser ecomUser) {
        try {
            userRepository.save(ecomUser);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body("Exception in registering"+ex.toString());
        }
        return ResponseEntity.ok().body("Added successfully");
    }

    @Override
    public Optional<EcomUser> findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

}

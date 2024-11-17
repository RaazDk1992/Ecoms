package com.RaazDk.eComs.controller;

import com.RaazDk.eComs.models.EcomUser;
import com.RaazDk.eComs.repository.EcomUserRepository;
import com.RaazDk.eComs.security.jwt.JwtUtils;
import com.RaazDk.eComs.security.requests.LoginRequest;
import com.RaazDk.eComs.security.requests.SignupRequest;
import com.RaazDk.eComs.security.response.LoginResponse;
import com.RaazDk.eComs.security.response.MessageResponse;
import com.RaazDk.eComs.security.response.UserInfoResponse;
import com.RaazDk.eComs.services.EcomUserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EcomUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginRequest loginRequest){

        Authentication authentication ;
        try {

            authentication =authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword()));


        }catch (AuthenticationException ex){
            Map<String, Object> map = new HashMap<>();
            map.put("message","Bad credentials");
            map.put("status","false");

            System.out.println(ex.getMessage());
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateTokenFromUsername(details);
        List<String> roles =details.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        LoginResponse loginResponse = new LoginResponse(jwtToken,roles,details.getUsername());
        return ResponseEntity.ok(loginResponse);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest userDetails){

       return userService.saveUser(userDetails);
    }


    @GetMapping("/getUser")
    public ResponseEntity getUser(@AuthenticationPrincipal UserDetails userDetails){

        EcomUser user = userService.findByUsername(userDetails.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        UserInfoResponse response = new UserInfoResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.isAccountNonLocked(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                user.getCredentialsExpiryDate(),
                user.getAccountExpiryDate(),
                user.is2faEnabled(),
                roles
        );

        return ResponseEntity.ok().body(response);

    }
}

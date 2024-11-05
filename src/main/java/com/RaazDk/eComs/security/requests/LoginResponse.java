package com.RaazDk.eComs.security.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LoginResponse {

    private String jwtToken;

    public LoginResponse(String jwtToken, List<String> roles, String userName) {
        this.jwtToken = jwtToken;
        this.roles = roles;
        this.userName = userName;
    }

    private List<String> roles;
    private String userName;
}

package com.RaazDk.eComs.security.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    private String userName;
    private String password;

}

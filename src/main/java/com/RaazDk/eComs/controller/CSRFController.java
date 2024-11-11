package com.RaazDk.eComs.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CSRFController {


    public CsrfToken getCsrfToken(HttpServletRequest request){

        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

}

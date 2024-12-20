package com.RaazDk.eComs.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CSRFController {


    @GetMapping("/api/getcsrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){

        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

}

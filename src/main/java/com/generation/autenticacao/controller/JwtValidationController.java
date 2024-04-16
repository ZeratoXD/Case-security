package com.generation.autenticacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.autenticacao.security.JwtValidationService;

@RestController
public class JwtValidationController {

    @Autowired
    private JwtValidationService jwtValidationService;

    @GetMapping("/validar-jwt")
    public boolean validarJwt(@RequestParam String jwt) {
        return jwtValidationService.validarJwt(jwt);
    }
}

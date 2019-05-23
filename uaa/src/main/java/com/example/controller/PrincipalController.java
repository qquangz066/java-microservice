package com.example.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class PrincipalController {

    @GetMapping
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping("/me")
    public Object infoUser() {
        try {
            final OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            return auth.getUserAuthentication().getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
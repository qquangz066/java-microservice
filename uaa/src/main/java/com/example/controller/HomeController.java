package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {
    @Autowired
    Environment environment;

    @GetMapping
    @PreAuthorize("#oauth2.clientHasRole('user:read')")
    public String index(){
        return "testhome";
    }

    @GetMapping("port")
    public String indax(){
        return environment.getProperty("local.server.port");
    }
}

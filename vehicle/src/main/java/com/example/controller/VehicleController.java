package com.example.controller;

import com.example.feign.UaaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vehicles")
public class VehicleController {

    @Autowired
    private UaaClient uaaClient;

    @GetMapping
    public String index(){
        return uaaClient.index();
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("test")
    public String test(){
        return "test";
    }
}

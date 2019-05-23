package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("software")
public class SoftwareController {



    @GetMapping
    public String index(){
        return "software";
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("test")
    public String test(){
        return "test";
    }
}

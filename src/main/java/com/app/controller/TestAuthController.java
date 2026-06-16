package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")//desde el principio niego todo
public class TestAuthController{

    @GetMapping("/hello")
    @PreAuthorize("permitAll()")//autorizo hello
    public String hello(){
        return "Hello world";
    }

    @GetMapping("/hello-secured")
    @PreAuthorize("hasAuthority('READ')")//autorizo helloSecured
    public String helloSecured(){
        return "Hello World Secured";
    } 
}

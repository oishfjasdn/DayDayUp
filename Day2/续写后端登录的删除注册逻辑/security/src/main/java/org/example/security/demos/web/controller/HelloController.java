package org.example.security.demos.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/hello")
    @PreAuthorize("@demo.hasAuthority('system:dept:list')")
    public String hello() {
        return "hello";
    }
}

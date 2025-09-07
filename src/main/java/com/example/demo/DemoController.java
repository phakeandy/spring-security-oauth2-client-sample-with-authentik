package com.example.demo;

import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {
    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello from public endpoint!";
    }

    @GetMapping("/secured/hello")
    @PreAuthorize("isAuthenticated()")
    public String securedHello(Principal principal) {
        // 从 Principal 对象中获取用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        return "Hello, " + username
                + "! You are accessing a secured endpoint. Your authorities are: "
                + authentication.getAuthorities();
    }
}

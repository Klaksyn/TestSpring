package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/")
    public String authPage() {
        return "Auth/login";
    }

    @PostMapping("/LogIn")
    public void LogInInProfile(
            @PathVariable String login,
            @PathVariable String password
    ) {
        // TODO
    }

    @GetMapping("/Register")
    public String registerPage() {
        return "Auth/register";
    }

    @PostMapping("/Register")
    public void registerUser(
            @PathVariable String fio,
            @PathVariable String email,
            @PathVariable String login,
            @PathVariable String password
    ) {
        // TODO
    }

}

package com.springboot.moviescrud.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/parent")
public class LoginController {

    @GetMapping("/login-page")
    public String showMyLoginPage() {
        return "fancy-login";

    }

    // add request mapping for /access-denied

    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";

    }

}

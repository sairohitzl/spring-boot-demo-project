package com.springboot.moviescrud.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/parent")
public class LoginController {

    @GetMapping("/login-page")
    public String showMyLoginPage() {
        return "new-login";

    }



    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";

    }

}

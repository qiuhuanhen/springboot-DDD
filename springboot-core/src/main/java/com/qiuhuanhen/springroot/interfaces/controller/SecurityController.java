package com.qiuhuanhen.springroot.interfaces.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class SecurityController {
    @GetMapping
    public String login() {

        return "susscess";
    }
}

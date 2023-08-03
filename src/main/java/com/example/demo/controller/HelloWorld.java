package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//localhost:8080?name=nishizawa
public class HelloWorld {
    @GetMapping("/hello")
    public String getHello(@RequestParam("name") String name) {
        return "Hello, " + name;
    }
}

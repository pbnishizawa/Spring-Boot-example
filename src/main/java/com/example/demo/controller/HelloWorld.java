package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.Bean.HelloWorldBean;
import com.fasterxml.jackson.core.sym.Name;

@RestController
@RequestMapping
//localhost:8080?name=nishizawa
public class HelloWorld {
    @GetMapping("/hello")
    public String getHello(@RequestParam("name") String name) {
        return "Hello, " + name;
    }
    
    //@RequestParamでリクエストパラメータを受け取る
    @PostMapping("/hello")
    public String postMethod(@RequestBody HelloWorldBean helloworldbean) {
        String name = helloworldbean.getName();
        return "Hello, " + name ;
    }
}

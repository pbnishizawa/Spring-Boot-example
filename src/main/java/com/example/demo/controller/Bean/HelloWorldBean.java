package com.example.demo.controller.Bean;

import java.io.Serializable;
public class HelloWorldBean implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HelloWorldBean() {
    }
    
    
}

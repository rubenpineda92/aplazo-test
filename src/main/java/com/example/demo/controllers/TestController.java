package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping
    public String helloMethod(){
           return "Hello im working"; 
    }

    @GetMapping("saludo")
    public String helloMethod2(){
           return "Hello im working"; 
    }
    
}

package com.springcloud.recommendservice.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {

    @RequestMapping("/recommend")
    public String read(){
        return "Spring in Action (Manning), Cloud Native Java (O'Reilly), Learning Spring Boot (Packt)";
    }

    @RequestMapping("/buy")
    public String buy(){
        return "Java 8 in Action";
    }
}

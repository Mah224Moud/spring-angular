package com.mamoudou.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "essai")
public class EssaiController {

    @GetMapping
    public Map<String, String> hello(){
        return Map.of("message", "hello les gens !");
    }

    @GetMapping(path = "/autre")
    public String essai(){
        return "Je suis ici";
    }
}

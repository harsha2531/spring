package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {
    public HelloController() {
        System.out.println("HelloController");
    }

//    @GetMapping("/test1")
    @GetMapping
    public String sayHello() {
//    return "Hello World";
        return "index";
    }

   /* @GetMapping("/test2")
    public String test(){
        return "Test";
    }*/
}

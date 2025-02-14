package org.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
public class HelloController {
    @GetMapping
    public String sayHello() {
        return "get mapping invoked";
    }
    @PostMapping
    public String sayPostHello(){
        return "post mapping invoked";
    }
    @PutMapping
    public String sayPutHello(){
        return "put mapping invoked";
    }
    @DeleteMapping
    public String sayDeleteHello(){
        return "delete mapping invoked";
    }



}

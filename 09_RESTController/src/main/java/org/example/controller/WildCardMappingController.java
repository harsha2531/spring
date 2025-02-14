package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wildcard")
public class WildCardMappingController {

    @GetMapping(path = "test1/*/hello") //* eka thanata any map ekak denna puluwn
    public String test1(){
        return "get mapping 1 invoked";
    }

    @GetMapping(path = "test2/*/*")
    public String test2(){
        return "get mapping 2 invoked";
    }
}

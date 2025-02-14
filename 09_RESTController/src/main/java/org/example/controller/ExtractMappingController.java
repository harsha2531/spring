package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("extract")
public class ExtractMappingController {
    @GetMapping(path = "test1")
    public String test1(){
        return "test1 method invoked";
    }
    @GetMapping(path = "test2/harsha") //ambigous problem eka solve karaganna path deela set kranwa
    public String test2(){
        return "test2 method invoked";
    }

}

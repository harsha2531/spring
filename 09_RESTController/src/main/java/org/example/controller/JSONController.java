package org.example.controller;

import org.example.dto.CustomerDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("json")
public class JSONController {

    //json Object to java Object
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String test1(@RequestBody CustomerDTO customerDTO){
        return customerDTO.toString();
    }

    //java Object to json object
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO test2(){
        return new CustomerDTO("Charith","Venura",24);
    }

    @GetMapping(path = "getAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CustomerDTO>test3(){
        ArrayList<CustomerDTO> list = new ArrayList<>();
        list.add(new CustomerDTO("Charith","Venura",24));
        list.add(new CustomerDTO("Harsha","Nimeda",26));
        list.add(new CustomerDTO("Gihan","Chamod",30));
        return list;

    }
}


package org.example.h13_spring_boot.controller;

import org.example.h13_spring_boot.dto.CustomerDTO;
import org.example.h13_spring_boot.entity.Customer;
import org.example.h13_spring_boot.repo.CustomerRepo;
import org.example.h13_spring_boot.service.impl.CustomerServiceImpl;
import org.example.h13_spring_boot.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin(origins = "http://localhost:63342", allowedHeaders = "*")
public class CustomerController{
    //property injection
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private CustomerRepo customerRepo;

    @PostMapping("save")
    public ResponseUtil saveCustomer(@RequestBody CustomerDTO customerDTO){
//        System.out.println(customerDTO.getName());
//        return customerDTO.toString();
        boolean res = customerService.addCustomer(customerDTO);
        if(res){
            return new ResponseUtil(201,"Customer Saved",null);
        }else {
            return new ResponseUtil(200, "Exists Customer", null);
        }
    }

    @GetMapping("getAll")
    public List<CustomerDTO> getAllCustomers(){
       return customerService.getAllCustomers();
    }

    @PutMapping("update")
    public CustomerDTO updateCustomer( @RequestBody CustomerDTO customerDTO){
        Customer updatedCustomer = customerService.updateCustomer(customerDTO);
        return new CustomerDTO(
                updatedCustomer.getId(),
                updatedCustomer.getName(),
                updatedCustomer.getAddress()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted with id: " + id + " successfully");
    }


}

package org.example.h13_spring_boot.controller;

import org.example.h13_spring_boot.dto.CustomerDTO;
import org.example.h13_spring_boot.entity.Customer;
import org.example.h13_spring_boot.repo.CustomerRepo;
import org.example.h13_spring_boot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController{
    //property injection
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;

    @PostMapping("save")
    public boolean saveCustomer(@RequestBody CustomerDTO customerDTO){
//        System.out.println(customerDTO.getName());
//        return customerDTO.toString();
        customerService.addCustomer(customerDTO);
        return true;
    }

    @GetMapping("getAll")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
                return customers.stream().map(customer -> new CustomerDTO(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress()
                )).collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public CustomerDTO updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO){
        Customer updatedCustomer = customerService.updateCustomer(id, customerDTO);
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

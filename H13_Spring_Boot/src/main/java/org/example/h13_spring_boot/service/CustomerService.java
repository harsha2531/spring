package org.example.h13_spring_boot.service;

import org.example.h13_spring_boot.dto.CustomerDTO;
import org.example.h13_spring_boot.entity.Customer;
import org.example.h13_spring_boot.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public boolean addCustomer(CustomerDTO customerDTO) {
//        System.out.println("Service Method"+customerDTO.getAddress());
        Customer customer = new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getAddress());
        customerRepo.save(customer);
        return true;
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer updateCustomer(int id, CustomerDTO customerDTO){
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        return customerRepo.save(customer);
    }

    public void deleteCustomer(int id){
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
        customerRepo.delete(customer);
    }


}

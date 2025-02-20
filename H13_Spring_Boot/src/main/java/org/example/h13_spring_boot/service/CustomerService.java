package org.example.h13_spring_boot.service;

import org.example.h13_spring_boot.dto.CustomerDTO;
import org.example.h13_spring_boot.entity.Customer;

import java.util.List;

public interface CustomerService {
    public boolean addCustomer(CustomerDTO customerDTO);

    public List<CustomerDTO> getAllCustomers();

    public Customer updateCustomer(CustomerDTO customerDTO);

    public void deleteCustomer(int id);
}

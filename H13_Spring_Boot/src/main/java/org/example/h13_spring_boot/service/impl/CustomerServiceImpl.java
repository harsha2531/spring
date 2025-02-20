package org.example.h13_spring_boot.service.impl;

import org.example.h13_spring_boot.dto.CustomerDTO;
import org.example.h13_spring_boot.entity.Customer;
import org.example.h13_spring_boot.repo.CustomerRepo;
import org.example.h13_spring_boot.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) {
//        System.out.println("Service Method"+customerDTO.getAddress());
       /* Customer customer = new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getAddress());*/
        if(customerRepo.existsById((customerDTO.getId()))){
            throw new RuntimeException("Customer already exists");
        }else {
//        Customer customer = modelMapper.map(customerDTO, Customer.class);
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
//        customerRepo.save(customer);
//        customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return true;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
//        return customerRepo.findAll();
        /*List<Customer> customerList = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList ){
            customerDTOList.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress()));
        }*/
        return modelMapper.map(customerRepo.findAll(),
                new TypeToken<List<CustomerDTO>>() {}.getType());
    }

    @Override
    public Customer updateCustomer(CustomerDTO customerDTO){
        Customer customer = customerRepo.findById(customerDTO.getId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + customerDTO.getId()));
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        return customerRepo.save(customer);
    }

    @Override
    public void deleteCustomer(int id){
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
        customerRepo.delete(customer);
    }


}

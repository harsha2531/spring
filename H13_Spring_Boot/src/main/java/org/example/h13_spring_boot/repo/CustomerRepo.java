package org.example.h13_spring_boot.repo;

import jakarta.persistence.Entity;
import org.example.h13_spring_boot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

   /* //Query Methods
    List<Customer>findByAddress(String address);
    List<Customer>readCustomerByAddress(String address);
    List<Customer>getCustomerByName(String name);
    long countCustomerByAddress(String address);

    //Native Query
    @Query(value = "select * from Customer", nativeQuery = true)
    List<Customer>getallCustomers();

    @Query(value = "select * from Customer where name=?1", nativeQuery = true)
    Customer searchcustomerbyName(String name);*/


}

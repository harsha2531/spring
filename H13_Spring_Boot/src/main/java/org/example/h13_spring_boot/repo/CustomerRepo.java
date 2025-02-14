package org.example.h13_spring_boot.repo;

import jakarta.persistence.Entity;
import org.example.h13_spring_boot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}

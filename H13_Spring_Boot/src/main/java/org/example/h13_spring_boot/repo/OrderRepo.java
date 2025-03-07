package org.example.h13_spring_boot.repo;

import org.example.h13_spring_boot.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT id FROM orders ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String getLastOrderId();
}

package org.example.h13_spring_boot.repo;

import org.example.h13_spring_boot.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepo extends CrudRepository<OrderDetail, Integer> {
}

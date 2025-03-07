package org.example.h13_spring_boot.repo;

import org.example.h13_spring_boot.dto.OrderDetailDTO;
import org.example.h13_spring_boot.entity.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepo extends CrudRepository<OrderDetail, Integer> {

}
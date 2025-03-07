/*
package org.example.h13_spring_boot.service;

import org.example.h13_spring_boot.entity.OrderDetail;
import org.example.h13_spring_boot.entity.Orders;

import java.util.List;

public interface OrderService {
    public void placeOrder(Orders order, List<OrderDetail> orderDetails);
    public List<Orders> getAllOrders();
}
*/

package org.example.h13_spring_boot.service;

import org.example.h13_spring_boot.dto.OrderDTO;
import org.example.h13_spring_boot.dto.OrderDetailDTO;

import java.util.List;

public interface OrderService {
    void placeOrder(OrderDTO orderDTO);
}

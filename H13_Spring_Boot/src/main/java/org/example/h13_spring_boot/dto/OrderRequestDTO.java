package org.example.h13_spring_boot.dto;

import org.example.h13_spring_boot.entity.OrderDetail;
import org.example.h13_spring_boot.entity.Orders;

import java.util.List;

public class OrderRequestDTO {
    private Orders order;
    private List<OrderDetail> orderDetails;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(Orders order, List<OrderDetail> orderDetails) {
        this.order = order;
        this.orderDetails = orderDetails;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                "order=" + order +
                ", orderDetails=" + orderDetails +
                '}';
    }
}

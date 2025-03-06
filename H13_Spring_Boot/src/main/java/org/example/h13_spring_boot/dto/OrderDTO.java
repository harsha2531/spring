/*
package org.example.h13_spring_boot.dto;

import java.sql.Date;

public class OrdersDTO {
    private int id;
    private Date date;
    private int customerId;

    public OrdersDTO() {
    }

    public OrdersDTO(int id, Date date, int customerId) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
*/
package org.example.h13_spring_boot.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private int id;
    private LocalDate date;
    private int customerId;
    private List<OrderDetailDTO> orderDetails;

    public OrderDTO() {
    }

    public OrderDTO(int id, LocalDate date, int customerId, List<OrderDetailDTO> orderDetails) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
        this.orderDetails = orderDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}


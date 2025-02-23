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

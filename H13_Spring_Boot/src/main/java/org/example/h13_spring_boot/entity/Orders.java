package org.example.h13_spring_boot.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class Orders {
    @Id
    private int id;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail>orderDetails;

    public Orders() {
    }

    public Orders(int id, Date date, Customer customer, List<OrderDetail> orderDetails) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.orderDetails = orderDetails;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}

package org.example.h13_spring_boot.entity;

import jakarta.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int qty;
    private double total_price;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;

    public OrderDetail() {
    }

    public OrderDetail(int id, int qty, double total_price, Item item, Orders orders) {
        this.id = id;
        this.qty = qty;
        this.total_price = total_price;
        this.item = item;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", qty=" + qty +
                ", total_price=" + total_price +
                ", item=" + item +
                ", orders=" + orders +
                '}';
    }
}


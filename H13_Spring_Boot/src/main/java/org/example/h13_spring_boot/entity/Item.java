package org.example.h13_spring_boot.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Item {
    @Id
    private int id;
    private String name;
    private String price;
    private int qtyOnHand;

    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderDetail>orderDetails;

    public Item() {
    }

    public Item(int id, String name, String price, int qtyOnHand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qtyOnHand = qtyOnHand;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }


}

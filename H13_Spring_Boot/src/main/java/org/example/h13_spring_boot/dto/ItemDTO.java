package org.example.h13_spring_boot.dto;

public class ItemDTO {
    private int id;
    private String name;
    private double price;
    private int qtyOnHand;

    public ItemDTO() {
    }

    public ItemDTO(int id, String name, double price, int qtyOnHand) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}

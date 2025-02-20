package org.example.h13_spring_boot.dto;

public class ItemDTO {
    private int id;
    private String name;
    private String price;
    private String qtyOnHand;

    public ItemDTO() {
    }

    public ItemDTO(int id, String name, String price, String qtyOnHand) {
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

    public String getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(String qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}

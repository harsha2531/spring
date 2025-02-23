package org.example.h13_spring_boot.dto;

public class OrderDetailDTO {
    private int qty;
    private String total_price;
    private int order_id;
    private int item_id;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int qty, String total_price, int order_id, int item_id) {
        this.qty = qty;
        this.total_price = total_price;
        this.order_id = order_id;
        this.item_id = item_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
}

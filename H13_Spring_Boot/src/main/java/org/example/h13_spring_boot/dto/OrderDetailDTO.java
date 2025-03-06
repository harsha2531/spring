/*
package org.example.h13_spring_boot.dto;

public class OrderDetailDTO {
    private int qty;
    private double total_price;
    private int order_id;
    private int item_id;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int qty, double total_price, int order_id, int item_id) {
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

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
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
*/

package org.example.h13_spring_boot.dto;

public class OrderDetailDTO {
    private int itemId;
    private int quantity;
    private double totalPrice;
    private double unitPrice;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int itemId, int quantity, double totalPrice, double unitPrice) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.unitPrice = unitPrice;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}


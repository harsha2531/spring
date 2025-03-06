package org.example.h13_spring_boot.service.impl;

import org.example.h13_spring_boot.entity.Item;
import org.example.h13_spring_boot.entity.OrderDetail;
import org.example.h13_spring_boot.entity.Orders;
import org.example.h13_spring_boot.repo.ItemRepo;
import org.example.h13_spring_boot.repo.OrderDetailRepo;
import org.example.h13_spring_boot.repo.OrdersRepo;
import org.example.h13_spring_boot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Transactional
    public void placeOrder(Orders order, List<OrderDetail> orderDetails) {
        System.out.println("Place order: "+order);
        ordersRepo.save(order);

        for(OrderDetail orderDetail : orderDetails){
            Item item = orderDetail.getItem();

            //check if stock is available
            if(item.getQtyOnHand() < orderDetail.getQty()){
                throw new RuntimeException("Not enough stock for item:"+item.getName());
            }

            //reduce item stock
            item.setQtyOnHand(item.getQtyOnHand()-orderDetail.getQty());
            itemRepo.save(item);

            orderDetail.setOrders(order);
            orderDetailRepo.save(orderDetail);
        }
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepo.findAll();
    }

}

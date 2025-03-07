/*
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
*/

package org.example.h13_spring_boot.service.impl;

import jakarta.persistence.criteria.Order;
import org.example.h13_spring_boot.dto.OrderDTO;
import org.example.h13_spring_boot.dto.OrderDetailDTO;
import org.example.h13_spring_boot.entity.*;
import org.example.h13_spring_boot.repo.CustomerRepo;
import org.example.h13_spring_boot.repo.ItemRepo;
import org.example.h13_spring_boot.repo.OrderDetailRepo;
import org.example.h13_spring_boot.repo.OrderRepo;
import org.example.h13_spring_boot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Override
    public void placeOrder(OrderDTO orderDTO) {
        Customer customer = customerRepo.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Orders order = new Orders();
        order.setDate(orderDTO.getDate());
        order.setCustomer(customer);

        List<OrderDetail> orderDetails = orderDTO.getOrderDetails().stream()
                .map(detailDTO -> {
                    Item item = itemRepo.findById(detailDTO.getItemId())
                            .orElseThrow(() -> new RuntimeException("Item not found"));

                    if (item.getQtyOnHand() < detailDTO.getQuantity()) {
                        throw new RuntimeException("Not enough stock for item: " + item.getName());
                    }

                    item.setQtyOnHand(item.getQtyOnHand() - detailDTO.getQuantity());
                    return new OrderDetail(order, item, detailDTO.getQuantity(), detailDTO.getUnitPrice());
                })
                .collect(Collectors.toList());

        order.setOrderDetails(orderDetails);
        orderRepo.save(order);
    }


   /* public List<OrderDTO> getAllOrders() {
        List<Orders> order = orderRepo.findAll();
        return order.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public String generateNextOrderId() {
        String lastId = orderRepo.getLastOrderId();
        if (lastId == null) {
            return "ORD-001";
        } else {
            int idNum = Integer.parseInt(lastId.split("-")[1]);
            return String.format("ORD-%03d", idNum + 1);
        }
    }

    public OrderDTO getOrderById(Integer orderId) {
        Orders order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDTO(order);
    }
    private OrderDTO convertToDTO(Order order) {
        List<OrderDetailDTO> details = order.getOrderDetails().stream()
                .map(detail -> new OrderDetailDTO(
                        detail.getItemId(),
                        detail.getQuantity(),
                        detail.getUnitPrice()
                )).collect(Collectors.toList());

        return new OrderDTO(order.getId(), order.getDate(), order.getCustomerId(), details);
    }*/
}


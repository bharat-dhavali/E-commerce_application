package com.example.OrderService.Service;

import com.example.OrderService.Entity.Order;
import com.example.OrderService.Model.OrderRequest;
import com.example.OrderService.Repository.OrderRepository;
import com.example.OrderService.external.client.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;
    @Override
    public long placeOrder(OrderRequest orderRequest) {
        //Order Entity -> Save the data with status order created.
        //product service -> block products(Reduce the Quntity)
        //payment service -> payments -> success -> COMPLETE,Else CANCELLED

        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());  //feign clien producrservice
        log.info("Creating Order with Status CREATED");
        Order order=Order.builder()
                .orderStatus("CREATED")
                .amount(orderRequest.getTotalAmount())
                .productId(orderRequest.getProductId())
                .orderData(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        order=orderRepository.save(order);

        log.info("Order placed successfuly with order Id : {}",order.getId());

        return order.getId();
    }
}

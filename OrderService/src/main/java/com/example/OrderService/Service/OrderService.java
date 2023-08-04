package com.example.OrderService.Service;

import com.example.OrderService.Model.OrderRequest;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
}

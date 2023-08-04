package com.bharat.ProductService.service;

import com.bharat.ProductService.model.ProductRequest;
import com.bharat.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);


    ProductResponse getProductById(long productId);

    void reduceQuantity(long productid, long quantity);
}

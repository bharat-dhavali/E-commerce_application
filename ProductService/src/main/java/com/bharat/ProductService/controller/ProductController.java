package com.bharat.ProductService.controller;

import com.bharat.ProductService.entity.Product;
import com.bharat.ProductService.model.ProductRequest;
import com.bharat.ProductService.model.ProductResponse;
import com.bharat.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest)
    {
        long productId =productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId)
    {

        ProductResponse productResponse= productService.getProductById(productId);


        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @PutMapping("reduce/quantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productid,@RequestParam long quantity)
    {
        productService.reduceQuantity(productid,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

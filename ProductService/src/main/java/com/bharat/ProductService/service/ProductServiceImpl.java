package com.bharat.ProductService.service;

import com.bharat.ProductService.entity.Product;
import com.bharat.ProductService.exception.ProductServiceCustomException;
import com.bharat.ProductService.model.ProductRequest;
import com.bharat.ProductService.model.ProductResponse;
import com.bharat.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
       log.info("adding product...");
        Product product=Product.builder().productname(productRequest.getName()).quantity(productRequest.getQuantity()).price(productRequest.getPrice()).build();

        productRepository.save(product);
        log.info("product created...");
        return product.getProductid();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("getting product by id");

        Product product=productRepository.findById(productId)
                .orElseThrow(()-> new ProductServiceCustomException("Product with given Id not found","PRODUCT_NOT_FOUND"));
        ProductResponse productResponse=new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);



        return productResponse;
    }

    @Override
    public void reduceQuantity(long productid, long quantity) {
        log.info("Reduce Quntity {} for Id: {}",quantity,productid);
        Product product=productRepository.findById(productid)
                .orElseThrow(() ->new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));

        if(product.getQuantity() <quantity)
        {
          throw new ProductServiceCustomException("Product does not have sufficient Quantity","INSUFFICIENT_QUANTITY");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product Quantity updateed Succesfully");


    }


}

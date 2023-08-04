package com.bharat.ProductService.exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends  RuntimeException{

    private String errorCode;
    public ProductServiceCustomException(String meassage,String errorCode)
    {
        super(meassage);
        this.errorCode=errorCode;
    }
}

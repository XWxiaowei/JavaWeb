package com.jay.jackson.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author xiang.wei
 * @Desc 产品
 * @date 2017/6/21 12:48
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVo {

    @JsonSerialize(using=ToStringSerializer.class)
    private Long productId;

    private String productName;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductVo(Long productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public ProductVo() {
    }
}

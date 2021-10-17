package io.recruitment.assessment.api.cart;

import io.recruitment.assessment.api.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class CartDto {

    private Long id;
    private Collection<Product> products = new ArrayList<>();
    private Long userId;
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}

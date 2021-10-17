package io.recruitment.assessment.api.product;

import io.recruitment.assessment.api.cart.Cart;
import io.recruitment.assessment.api.order.Order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(precision = 11, scale = 2)
    private BigDecimal price;

    @ManyToMany(mappedBy = "products")
    private Collection<Cart> carts;

    @ManyToMany(mappedBy = "products")
    private Collection<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

package io.avalia.shop.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Class representing the Orders Entity
 */
@Entity
public class OrderEntity implements Serializable {
    @Id
    @GeneratedValue
    private int orderId;

    private String ownerId;

    @OneToMany
    @JoinColumn(name = "productId", nullable = false)
    private List<ProductEntity> products;

    public int getOrderId() {
        return orderId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
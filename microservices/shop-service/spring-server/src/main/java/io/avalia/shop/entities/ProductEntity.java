package io.avalia.shop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Class representing the Product Entity
 */
@Entity
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue
    private int productId;

    private String name;
    private int price;

    public int getId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

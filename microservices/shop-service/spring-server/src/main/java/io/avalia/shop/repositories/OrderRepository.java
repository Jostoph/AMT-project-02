package io.avalia.shop.repositories;

import io.avalia.shop.entities.OrderEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for User Orders
 */
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

}

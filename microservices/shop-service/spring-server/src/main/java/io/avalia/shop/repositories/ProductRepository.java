package io.avalia.shop.repositories;

import io.avalia.shop.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Shop Products
 */
public interface ProductRepository extends CrudRepository<ProductEntity, Integer>{

    Page<ProductEntity> findAll(Pageable pageable);
}

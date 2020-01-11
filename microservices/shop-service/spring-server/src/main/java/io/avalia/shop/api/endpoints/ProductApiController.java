package io.avalia.shop.api.endpoints;

import io.avalia.shop.business.AuthorizationService;
import io.avalia.shop.business.models.AuthInfo;
import io.avalia.shop.entities.ProductEntity;
import io.avalia.shop.repositories.ProductRepository;
import io.avalia.users.api.ProductsApi;
import io.avalia.users.api.model.Product;
import io.avalia.users.api.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ProductApiController implements ProductsApi {

    @Autowired
    AuthorizationService auth;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity<Void> addProduct(String authorization, @Valid ProductDTO product) {

        AuthInfo info = (AuthInfo) request.getAttribute("auth-info");

        if(info.isAdmin()) {
            ProductEntity entity = toProductEntity(product);
            productRepository.save(entity);

            return ResponseEntity.status(HttpStatus.CREATED).build();

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Integer productId, String authorization) {

        AuthInfo info = (AuthInfo) request.getAttribute("auth-info");

        if(info.isAdmin()) {

            if(productRepository.existsById(productId)) {
                productRepository.deleteById(productId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Override
    public ResponseEntity<Product> getProduct(Integer productId, String authorization) {

        if(productRepository.existsById(productId)) {
            Product product = toProduct(productRepository.findById(productId).get());
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<Product>> getProducts(String authorization, @Valid Integer page, @Valid Integer size) {
        // TODO : check that size > 0 and send appropriate response
        Page<ProductEntity> productPage =  productRepository.findAll(PageRequest.of(page, size));

        List<Product> products = productPage.map(e -> toProduct(e)).toList();

        return ResponseEntity.ok(products);
    }

    private ProductEntity toProductEntity(ProductDTO productDTO) {
        ProductEntity entity = new ProductEntity();
        entity.setName(productDTO.getName());
        entity.setPrice(productDTO.getPrice());
        return entity;
    }

    private Product toProduct(ProductEntity entity) {
        Product product = new Product();
        product.setProductId(entity.getId());
        product.setName(entity.getName());
        product.setPrice(entity.getPrice());
        return product;
    }
}

package io.avalia.shop.api.endpoints;

import io.avalia.shop.business.AuthorizationService;
import io.avalia.shop.business.models.AuthInfo;
import io.avalia.shop.entities.OrderEntity;
import io.avalia.shop.entities.ProductEntity;
import io.avalia.shop.repositories.OrderRepository;
import io.avalia.shop.repositories.ProductRepository;
import io.avalia.users.api.ShopApi;
import io.avalia.users.api.model.Order;
import io.avalia.users.api.model.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ShopApiController implements ShopApi {

    @Autowired
    AuthorizationService auth;

    @Autowired
    HttpServletRequest request;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity<Void> deleteOrder(String authorization, Integer orderId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Order>> getOrders(String authorization) {
        return null;
    }

    @Override
    public ResponseEntity<Void> makeOrder(String authorization, @Valid OrderDTO order) {

        // TODO check products ?
        AuthInfo info = (AuthInfo) request.getAttribute("auth-info");

        if(info.getEmail().equals(order.getOwnerId())) {
            OrderEntity entity = toOrderEntity(order);
            orderRepository.save(entity);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    private OrderEntity toOrderEntity(OrderDTO orderDTO) {
        OrderEntity entity = new OrderEntity();
        entity.setOwnerId(orderDTO.getOwnerId());
        Iterable<ProductEntity> productEntityIterable = productRepository.findAllById(orderDTO.getProducts());
        List<ProductEntity> products = new ArrayList<>();
        for (ProductEntity p : productEntityIterable) {
            products.add(p);
        }
        entity.setProducts(products);
        return entity;
    }
}

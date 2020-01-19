package io.avalia.shop.api.endpoints;

import io.avalia.shop.business.AuthorizationService;
import io.avalia.shop.business.models.AuthInfo;
import io.avalia.shop.entities.OrderEntity;
import io.avalia.shop.entities.ProductEntity;
import io.avalia.shop.repositories.OrderRepository;
import io.avalia.shop.repositories.ProductRepository;
import io.avalia.shop.api.ShopApi;
import io.avalia.shop.api.model.Order;
import io.avalia.shop.api.model.OrderDTO;
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

        AuthInfo info = (AuthInfo) request.getAttribute("auth-info");

        if(info.isAdmin()) {
            if(orderRepository.existsById(orderId)) {
                orderRepository.deleteById(orderId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Override
    public ResponseEntity<List<Order>> getOrders(String authorization, String userId) {

        AuthInfo info = (AuthInfo) request.getAttribute("auth-info");

        if(info.isAdmin() || (info.getEmail().equals(userId))) {

            List<OrderEntity> orderEntities = orderRepository.findByOwnerId(userId);
            List<Order> orders = new ArrayList<>();
            for (OrderEntity entity : orderEntities) {
                orders.add(toOrder(entity));
            }

            return ResponseEntity.ok(orders);

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Override
    public ResponseEntity<Void> makeOrder(String authorization, @Valid OrderDTO order) {

        AuthInfo info = (AuthInfo) request.getAttribute("auth-info");

        if(info.getEmail().equals(order.getOwnerId())) {

            Iterable<ProductEntity> validProducts = productRepository.findAllById(order.getProducts());
            List<Integer> productIds = new ArrayList<>();
            for (ProductEntity pe : validProducts) {
                productIds.add(pe.getId());
            }

            // some products are not existing
            if(order.getProducts().size() != productIds.size()) {
                return ResponseEntity.badRequest().build();
            } else {
                OrderEntity entity = toOrderEntity(order);
                orderRepository.save(entity);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
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

    private Order toOrder(OrderEntity entity) {
        Order order = new Order();
        order.setOrderId(entity.getOrderId());
        order.setOwnerId(entity.getOwnerId());
        List<Integer> productIds = new ArrayList<>();
        for (ProductEntity pe : entity.getProducts()) {
            productIds.add(pe.getId());
        }
        order.setProducts(productIds);
        return order;
    }
}

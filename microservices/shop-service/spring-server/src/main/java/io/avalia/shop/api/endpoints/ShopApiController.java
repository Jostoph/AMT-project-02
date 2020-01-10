package io.avalia.shop.api.endpoints;

import io.avalia.shop.business.AuthorizationService;
import io.avalia.users.api.ShopApi;
import io.avalia.users.api.model.Order;
import io.avalia.users.api.model.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ShopApiController implements ShopApi {

    @Autowired
    AuthorizationService auth;

    @Autowired
    HttpServletRequest request;

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
        return null;
    }
}
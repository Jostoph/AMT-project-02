package io.avalia.shop.api.endpoints;

import io.avalia.shop.business.AuthorizationService;
import io.avalia.users.api.ProductsApi;
import io.avalia.users.api.model.Product;
import io.avalia.users.api.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ProductApiController implements ProductsApi {

    @Autowired
    AuthorizationService auth;

    @Autowired
    HttpServletRequest request;

    @Override
    public ResponseEntity<Void> addProduct(String authorization, @Valid ProductDTO product) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Integer productId, String authorization) {
        return null;
    }

    @Override
    public ResponseEntity<Product> getProduct(Integer productId, String authorization) {
        return null;
    }

    @Override
    public ResponseEntity<List<Product>> getProducts(String authorization, Integer fromPage, Integer maxShowed) {
        return null;
    }
}

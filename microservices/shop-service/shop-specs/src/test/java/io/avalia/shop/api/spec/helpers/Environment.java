package io.avalia.shop.api.spec.helpers;

import io.avalia.shop.ApiException;
import io.avalia.shop.ApiResponse;
import io.avalia.shop.api.DefaultApi;
import io.avalia.shop.api.dto.OrderDTO;
import io.avalia.shop.api.dto.Product;
import io.avalia.shop.api.dto.ProductDTO;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private DefaultApi api = new DefaultApi();

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;

    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private String token;
    private ProductDTO product;
    private OrderDTO orderDTO;

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("io.avalia.shop.server.url");
        api.getApiClient().setBasePath(url);

    }

    public void setLastApiResponse(ApiResponse lastApiResponse) {
        this.lastApiResponse = lastApiResponse;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public DefaultApi getApi() {
        return api;
    }

    public void setLastApiCallThrewException(boolean lastApiCallThrewException) {
        this.lastApiCallThrewException = lastApiCallThrewException;
    }

    public void setLastStatusCode(int lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }

    public boolean isLastApiCallThrewException() {
        return lastApiCallThrewException;
    }

    public ApiException getLastApiException() {
        return lastApiException;
    }

    public void setLastApiException(ApiException lastApiException) {
        this.lastApiException = lastApiException;
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public ApiResponse getLastApiResponse() {
        return lastApiResponse;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }
}
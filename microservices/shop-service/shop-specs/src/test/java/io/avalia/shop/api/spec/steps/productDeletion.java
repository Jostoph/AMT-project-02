package io.avalia.shop.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.avalia.shop.ApiException;
import io.avalia.shop.api.DefaultApi;
import io.avalia.shop.api.dto.ProductDTO;
import io.avalia.shop.api.spec.helpers.Environment;

import static org.junit.Assert.*;

public class productDeletion {

    private Environment environment;
    private DefaultApi api;

    public productDeletion(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^I create a product and POST it to /products$")
    public void i_create_a_product_and_POST_it_to_products() {
        ProductDTO product = new ProductDTO();
        product.setName("Product");
        product.setPrice(32);
        environment.setProduct(product);
        try {
            environment.setLastApiResponse(api.addProductWithHttpInfo(environment.getToken(), environment.getProduct()));
            environment.setLastApiCallThrewException(false);
            environment.setLastApiException(null);
            environment.setLastStatusCode(environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            environment.setLastApiCallThrewException(true);
            environment.setLastApiResponse(null);
            environment.setLastApiException(e);
            environment.setLastStatusCode(environment.getLastApiException().getCode());
        }
        assertFalse(environment.isLastApiCallThrewException());
    }


    @When("^I DELETE it to the /products endpoint with (\\d+) id$")
    public void iDELETEItToTheProductsEndpointWithId(int id) {
        try {
            environment.setLastApiResponse(api.deleteProductWithHttpInfo(id, environment.getToken()));
            environment.setLastApiCallThrewException(false);
            environment.setLastApiException(null);
            environment.setLastStatusCode(environment.getLastApiResponse().getStatusCode());
        } catch (ApiException e) {
            environment.setLastApiCallThrewException(true);
            environment.setLastApiResponse(null);
            environment.setLastApiException(e);
            environment.setLastStatusCode(environment.getLastApiException().getCode());
        }
    }
}

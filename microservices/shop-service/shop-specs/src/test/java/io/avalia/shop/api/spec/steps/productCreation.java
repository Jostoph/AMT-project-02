package io.avalia.shop.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.avalia.shop.ApiException;
import io.avalia.shop.api.DefaultApi;
import io.avalia.shop.api.dto.ProductDTO;
import io.avalia.shop.api.spec.helpers.Environment;

public class productCreation {

    private Environment environment;
    private DefaultApi api;

    public productCreation(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }


    @Given("^I have a valid admin token$")
    public void iHaveAValidAdminToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlzQWRtaW4iOnRydWUsImVtYWlsIjoicm9vdEBtYWlsLmNvbSJ9.fNQboD7-48jCNz2ZDjdhAut7VWO9KK26sU4jH-14cj0";
        environment.setToken(token);
    }

    @Given("^I have a product credential$")
    public void iHaveAProductCredential() {
        ProductDTO product = new ProductDTO();
        product.setName("Product");
        product.setPrice(32);
        environment.setProduct(product);
    }

    @When("^I POST it to the /products$")
    public void iPOSTItToTheProducts() {
        try {
            environment.setLastApiResponse(api.addProductWithHttpInfo(environment.getToken(),environment.getProduct()));
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

package io.avalia.shop.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.shop.ApiException;
import io.avalia.shop.ApiResponse;
import io.avalia.shop.api.dto.Product;
import io.avalia.shop.api.spec.helpers.Environment;
import io.avalia.shop.api.DefaultApi;
import jdk.nashorn.internal.parser.Token;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class productRetrieval {

    private Environment environment;
    private DefaultApi api;


    public productRetrieval(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^there is a Shop server$")
    public void there_is_a_shop_server() {
        assertNotNull(api);
    }

    @Given("^I have a valid token$")
    public void i_have_a_valid_token() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlzQWRtaW4iOnRydWUsImVtYWlsIjoicm9vdEBtYWlsLmNvbSJ9.fNQboD7-48jCNz2ZDjdhAut7VWO9KK26sU4jH-14cj0";
        environment.setToken(token);
    }

    @When("^I GET it to the /products endpoint with (\\d+) pages and (\\d+) size$")
    public void i_GET_it_to_the_products_endpoint_with_pages_and_size(int pages, int size) {
        try {
            environment.setLastApiResponse(api.getProductsWithHttpInfo(environment.getToken(), pages, size));
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


    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_statusCode(int statusCode) {
        assertEquals(statusCode, environment.getLastStatusCode());
    }

    @Then("^I receive an array list$")
    public void i_receive_an_ArrayList() {
        System.out.println(environment.getLastApiResponse().getData());
        ArrayList products = (ArrayList) environment.getLastApiResponse().getData();
        assertNotNull(products);
    }


    @Given("^I have an invalid token$")
    public void i_have_an_invalid_token() {
        environment.setToken("Bearer NOTGOODTOKENQiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlzQWRtaW4iOnRydWUsImVtYWlsIjoicm9vdEBtYWlsLmNvbSJ9.fNQboD7-48jCNz2ZDjdhAut7VWO9KK26sU4jH-14cj0");
    }


    @When("^I GET it to the /products endpoint with negative (\\d+) pages and (\\d+) size$")
    public void i_GET_it_to_the_products_endpoint_with_negative_Pages_And_Size(int pages, int size) {
        i_GET_it_to_the_products_endpoint_with_pages_and_size(-pages, size);
    }
}

package  io.avalia.shop.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.shop.ApiException;
import io.avalia.shop.ApiResponse;
import io.avalia.shop.api.dto.Product;
import io.avalia.shop.api.spec.helpers.Environment;
import io.avalia.shop.api.DefaultApi;
import jdk.nashorn.internal.parser.Token;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class productRetrieval {

    private Environment environment;
    private DefaultApi api;


    public productRetrieval(Environment environment){
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^there is a Shop server$")
    public void there_is_a_shop_server() {
        assertNotNull(api);
    }

    @Given("^I have a valid token$")
    public void i_have_a_valid_token() {
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlzQWRtaW4iOnRydWUsImVtYWlsIjoicm9vdEBtYWlsLmNvbSJ9.fNQboD7-48jCNz2ZDjdhAut7VWO9KK26sU4jH-14cj0";
        environment.setToken(token);
    }

    @When("^I GET it to the /products endpoint with (\\d+) pages and (\\d+) size$")
    public void iGETItToTheProductsEndpointWithPagesAndSize(int pages, int size) {
        try {
            environment.setLastApiResponse((ApiResponse) api.getProducts(environment.getToken(),pages,size));
            environment.setLastApiCallThrewException(false);
            environment.setLastApiException(null);
            environment.setLastStatusCode(environment.getLastApiResponse().getStatusCode());

        } catch (ApiException e){
            environment.setLastApiCallThrewException(true);
            environment.setLastApiResponse(null);
            environment.setLastApiException(e);
            environment.setLastStatusCode(environment.getLastApiException().getCode());
        }
    }


    @Then("^I receive a (\\d+) status code$")
    public void iReceiveAStatusCode(int statusCode) {
        assertEquals(statusCode, environment.getLastStatusCode());
    }

    @Then("^I receive a array list$")
    public void iReceiveAArrayList() {
        Product[] products = (Product[]) environment.getLastApiResponse().getData();
        assertNotNull(products);
    }


    @Given("^I have an invalid token$")
    public void iHaveAnInvalidToken() {
        environment.setToken("Bearer NOTGOODTOKENQiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlzQWRtaW4iOnRydWUsImVtYWlsIjoicm9vdEBtYWlsLmNvbSJ9.fNQboD7-48jCNz2ZDjdhAut7VWO9KK26sU4jH-14cj0");
    }


}

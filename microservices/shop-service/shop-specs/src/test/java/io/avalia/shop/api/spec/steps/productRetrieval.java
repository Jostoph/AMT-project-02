package  io.avalia.shop.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.shop.api.spec.helpers.Environment;
import io.avalia.users.api.DefaultApi;
import jdk.nashorn.internal.parser.Token;

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
    public void iHaveAValidToken() {
    //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlzQWRtaW4iOnRydWUsImVtYWlsIjoicm9vdEBtYWlsLmNvbSJ9.fNQboD7-48jCNz2ZDjdhAut7VWO9KK26sU4jH-14cj0
        Token token = new T
    }

    @When("^I POST it to the /products endpoint$")
    public void iPOSTItToTheProductsEndpoint() {
    }

    @Then("^I receive a (\\d+) status code$")
    public void iReceiveAStatusCode(int arg0) {
    }

    @Then("^I receive a array list$")
    public void iReceiveAArrayList() {
    }

    @When("^I POST it to the /products endpoint with a -(\\d+) page size$")
    public void iPOSTItToTheProductsEndpointWithAPageSize(int arg0) {
    }

    @Given("^I have an invalid token$")
    public void iHaveAnInvalidToken() {
    }
}

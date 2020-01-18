package io.avalia.users.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.users.ApiException;
import io.avalia.users.ApiResponse;
import io.avalia.users.api.DefaultApi;
import io.avalia.users.api.dto.Credentials;
import io.avalia.users.api.dto.Token;
import io.avalia.users.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuthenticationSteps {

    private Environment environment;
    private DefaultApi api;

    public AuthenticationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }


    @Given("^there is a Users server$")
    public void there_is_a_Users_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have an admin credentials payload$")
    public void i_have_an_admin_credentials_payload() throws Throwable {
        Credentials credentials = new Credentials();
        credentials.setEmail("root@mail.com");
        credentials.setPassword("root");
        environment.setCredentials(credentials);
    }

    @When("^I POST it to the /connection endpoint$")
    public void i_POST_it_to_the_connection_endpoint() throws Throwable {
        try {
            environment.setLastApiResponse(api.loginWithHttpInfo(environment.getCredentials()));
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
    public void i_receive_a_status_code(int statusCode) throws Throwable {
        assertEquals(statusCode, environment.getLastStatusCode());
    }

    @Then("^I receive a token$")
    public void i_receive_a_token() {
      Token token = (Token) environment.getLastApiResponse().getData();
      assertNotNull(token);
    }

}

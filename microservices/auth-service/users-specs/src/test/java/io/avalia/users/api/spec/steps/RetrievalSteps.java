package io.avalia.users.api.spec.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.users.ApiException;
import io.avalia.users.api.DefaultApi;
import io.avalia.users.api.dto.UserDTO;
import io.avalia.users.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

public class RetrievalSteps {

    private Environment environment;
    private DefaultApi api;

    public RetrievalSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @When("^I GET the /users endpoint$")
    public void i_GET_the_users_endpoint() throws Throwable {
        try {
            environment.setLastApiResponse(api.getUsersWithHttpInfo(environment.getToken().getToken()));
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

    @Then("^I receive an array of usersDTO$")
    public void i_receive_an_array_of_usersDTO() throws Throwable {
        List<UserDTO> users = (List<UserDTO>) environment.getLastApiResponse().getData();
        assert(users.size() > 0);
    }

    @When("^I GET the /users endpoint with invalid token$")
    public void i_GET_the_users_endpoint_with_invalid_token() throws Throwable {
        try {
            environment.setLastApiResponse(api.getUsersWithHttpInfo("invalid"));
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

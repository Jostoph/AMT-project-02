package io.avalia.users.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.users.ApiException;
import io.avalia.users.api.DefaultApi;
import io.avalia.users.api.dto.User;
import io.avalia.users.api.spec.helpers.Environment;

import java.lang.reflect.Array;

public class recoverySteps {

    private Environment environment;
    private DefaultApi api;

    public recoverySteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @When("^I POST it to the /get endpoint$")
    public void i_POST_it_to_the_get_endpoint() {
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

    @Then("^I receive a list of users$")
    public void i_receive_a_list_of_users() {
    //TODO comment verifier qu'on recoit bien une liste d'user
    }
}

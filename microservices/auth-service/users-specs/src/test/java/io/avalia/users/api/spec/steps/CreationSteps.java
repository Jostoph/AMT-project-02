package io.avalia.users.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.avalia.users.ApiException;
import io.avalia.users.api.DefaultApi;
import io.avalia.users.api.dto.User;
import io.avalia.users.api.spec.helpers.Environment;

public class CreationSteps {

    private Environment environment;
    private DefaultApi api;

    public CreationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^I have a user payload$")
    public void i_have_a_user_payload() throws Throwable {
        User user = new User();
        user.setEmail("bob@mail.com");
        user.setFirstName("Bob");
        user.setLastName("Doe");
        user.setIsAdmin(false);
        user.setPassword("1234");
        environment.setUser(user);
    }

    @When("^I POST it to the /users endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        try {
            environment.setLastApiResponse(api.createUserWithHttpInfo(environment.getToken().getToken(), environment.getUser()));
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

    @Given("^I have an existing user payload$")
    public void i_have_an_existing_user_payload() throws Throwable {
        User user = new User();
        user.setEmail("root@mail.com");
        user.setFirstName("Alice");
        user.setLastName("Doe");
        user.setIsAdmin(false);
        user.setPassword("1234");
        environment.setUser(user);
    }


}

package io.avalia.users.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.avalia.users.ApiException;
import io.avalia.users.ApiResponse;
import io.avalia.users.api.DefaultApi;
import io.avalia.users.api.dto.User;
import io.avalia.users.api.spec.helpers.Environment;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Olivier Liechti on 27/07/17.
 */
public class CreationSteps {

    private Environment environment;
    private DefaultApi api;

    User user;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public CreationSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^there is a Users server$")
    public void there_is_a_Users_server() throws Throwable {
        assertNotNull(api);
    }

    @Given("^I have a user payload$")
    public void i_have_a_user_payload() throws Throwable {
        user = new User();

        user.setEmail("user.mail@test.com");
        user.setLastName("user");
        user.setFirstName("firstName");
        user.setPassword("password");
        user.setIsAdmin(false);

    }

    @When("^I POST it to the /users endpoint$")
    public void i_POST_it_to_the_users_endpoint() throws Throwable {
        try {
            lastApiResponse = api.createUserWithHttpInfo("$2a$10$7qwTDPxatoPrwOseLhSwIugi75PObi8hZQtIG/wjE5CMSLyhxUoEu",user);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }


    }

    @Then("^I receive a (\\d+) status code$")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        //todo
        assertEquals(201, lastApiResponse.getStatusCode());
    }

}

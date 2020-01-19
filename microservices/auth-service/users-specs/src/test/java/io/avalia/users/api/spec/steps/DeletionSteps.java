package io.avalia.users.api.spec.steps;

import cucumber.api.java.en.When;
import io.avalia.users.ApiException;
import io.avalia.users.api.DefaultApi;
import io.avalia.users.api.spec.helpers.Environment;

public class DeletionSteps {

    private Environment environment;
    private DefaultApi api;

    public DeletionSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @When("^I POST it to the /users/bob@mail\\.com endpoint$")
    public void i_POST_it_to_the_users_bob_mail_com_endpoint() throws Throwable {
        try {
            environment.setLastApiResponse(api.deleteUserWithHttpInfo("bob@mail.com", environment.getToken().getToken()));
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

    @When("^I POST it to the /users/fake@mail\\.com endpoint$")
    public void i_POST_it_to_the_users_fake_mail_com_endpoint() throws Throwable {
        try {
            environment.setLastApiResponse(api.deleteUserWithHttpInfo("fake@mail.com", environment.getToken().getToken()));
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

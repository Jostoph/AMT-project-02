package io.avalia.shop.api.spec.steps;

import cucumber.api.java.en.When;
import io.avalia.shop.ApiException;
import io.avalia.shop.api.DefaultApi;
import io.avalia.shop.api.spec.helpers.Environment;

public class orderRetrieval {

    private Environment environment;
    private DefaultApi api;

    public orderRetrieval(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }


    @When("^I GET it to the /shop endpoint$")
    public void iGETItToTheShopEndpoint() {
        try {
            environment.setLastApiResponse(api.getOrdersWithHttpInfo(environment.getToken(),"root@mail.com"));
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

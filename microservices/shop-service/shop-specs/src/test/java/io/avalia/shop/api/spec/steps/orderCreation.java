package io.avalia.shop.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.avalia.shop.ApiException;
import io.avalia.shop.api.DefaultApi;
import io.avalia.shop.api.dto.OrderDTO;
import io.avalia.shop.api.spec.helpers.Environment;

import javax.validation.constraints.Max;
import java.util.LinkedList;
import java.util.List;

public class orderCreation {

    private Environment environment;
    private DefaultApi api;

    public orderCreation(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }


    @Given("^I have a order credential$")
    public void iHaveAOrderCredential() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOwnerId("root@mail.com");
        List<Integer> integers = new LinkedList<>();
        orderDTO.setProducts(integers);
        environment.setOrderDTO(orderDTO);

    }

    @When("^I POST it to the /shop endpoint$")
    public void iPOSTItToTheShopEndpoint() {
        try {
            environment.setLastApiResponse(api.makeOrderWithHttpInfo(environment.getToken(), environment.getOrderDTO()));
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

    @Given("^I have a invalid order credential$")
    public void iHaveAInvalidOrderCredential() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOwnerId("root@mail.com");
        List<Integer> integers = new LinkedList<>();
        integers.add(-23);
        orderDTO.setProducts(integers);
        environment.setOrderDTO(orderDTO);
    }
}

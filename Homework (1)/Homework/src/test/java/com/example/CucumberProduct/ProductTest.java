package com.example.CucumberProduct;

import com.example.SpringIntegrationTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductTest {
    private String URI = "http://localhost:8080/api/v1/product";
    private RestTemplate restTemplate = new RestTemplate();
    ;
    private ResponseEntity<String> response;
    private HttpHeaders headers;


    @Given("User set POST product service api endpoint")
    public void userSetPOSTProductServiceApiEndpoint() {
        System.out.println("Add URL :" + URI);
    }

    @When("User Set request HEADER for Product API")
    public void userSetRequestHEADERForProductAPI() {
        headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
    }

    //POST
    @And("User send a POST HTTP request for Product API")
    public void userSendAPOSTHTTPRequestForProductAPI() {
        String jsonBody = "{\"productId\":4,\"name\":\"Sua\",\"date\":\"2021-12-24\",\"quantity\":20}";
        System.out.println("\n\n" + jsonBody);
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
        System.out.println(headers);
        //POST Method to Add New Product
        response = restTemplate. postForEntity(URI, entity, String.class);
    }

    @Then("The status code for Product API is {int}")
    public void theStatusCodeForProductAPIis(int statusCode) {
        assertThat(response.getStatusCodeValue()).isEqualTo(statusCode);
    }

    //GET
    @When("User send a GET HTTP request for Product API")
    public void userSendAGETHTTPRequestForProductAPI() {
        restTemplate = new RestTemplate();
        response = restTemplate.getForEntity(URI, String.class);
    }

    //PUT
    @And("User send a PUT HTTP request for Product API")
    public void userSendAPUTHTTPRequestForProductAPI() {
        URI = URI + "/1?name=Ham";
        response = restTemplate.exchange(URI, HttpMethod.PUT, null, String.class);
    }

    //DELETE
    @Given("User set DELETE product service api endpoint")
    public void userSetDELETEProductServiceApiEndpoint() {
        URI = URI + "/1";
        System.out.println(URI);
    }

    @When("User send a DELETE HTTP request for Product API")
    public void userSendADELETEHTTPRequestForProductAPI() {
        restTemplate = new RestTemplate();
        response = restTemplate.exchange(URI, HttpMethod.DELETE, null, String.class);
    }

}
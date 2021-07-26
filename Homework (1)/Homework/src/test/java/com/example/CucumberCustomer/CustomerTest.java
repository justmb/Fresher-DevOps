package com.example.CucumberCustomer;

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

public class CustomerTest {
    private String URI = "http://localhost:8080/api/v1/customer";
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;
    private HttpHeaders headers;


    @Given("User set POST customer service api endpoint")
    public void userSetPOSTCustomerServiceApiEndpoint() {
        System.out.println("Add URL :" + URI);
    }

    @When("User Set request HEADER for Customer API")
    public void userSetRequestHEADER() {
        headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
    }

    //POST
    @And("User send a POST HTTP request for Customer API")
    public void userSendAPOSTHTTPRequestForCustomerAPI() {
        String jsonBody = "{\"customerId\":3,\"name\":\"Minh\",\"address\":\"Cau Giay, Ha Noi\",\"phone_number\":\"+84987654321\"}";
        System.out.println("\n\n" + jsonBody);
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
        System.out.println(headers);
        //POST Method to Add New Customer
        response = restTemplate. postForEntity(URI, entity, String.class);
    }

    @Then("The status code for Customer API is {int}")
    public void theStatusCodeForCustomerAPIis(int statusCode) {
        assertThat(response.getStatusCodeValue()).isEqualTo(statusCode);
    }

    //GET
    @When("User send a GET HTTP request for Customer API")
    public void userSendAGETHTTPRequestForCustomerAPI() {
        restTemplate = new RestTemplate();
        response = restTemplate.getForEntity(URI, String.class);
    }

    //PUT
    @And("User send a PUT HTTP request for Customer API")
    public void userSendAPUTHTTPRequestForCustomerAPI() {
        URI = URI + "/1?name=Dang";
        response = restTemplate.exchange(URI, HttpMethod.PUT, null, String.class);
    }

    //DELETE
    @Given("User set DELETE customer service api endpoint")
    public void userSetDELETECustomerServiceApiEndpoint() {
        URI = URI + "/1";
        System.out.println(URI);
    }

    @When("User send a DELETE HTTP request for Customer API")
    public void userSendADELETEHTTPRequestForCustomerAPI() {
        restTemplate = new RestTemplate();
        response = restTemplate.exchange(URI, HttpMethod.DELETE, null, String.class);
    }

}
package com.example.CucumberInvoice;

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

public class InvoiceTest {
    private String URI = "http://localhost:8080/api/v1/invoice";
    private RestTemplate restTemplate = new RestTemplate();
    ;
    private ResponseEntity<String> response;
    private HttpHeaders headers;


    @Given("User set POST invoice service api endpoint")
    public void userSetPOSTInvoiceServiceApiEndpoint() {
        System.out.println("Add URL :" + URI);
    }

    @When("User Set request HEADER for Invoice API")
    public void userSetRequestHEADERForInvoiceAPI() {
        headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
    }

    //POST
    @And("User send a POST HTTP request for Invoice API")
    public void userSendAPOSTHTTPRequestForInvoiceAPI() {
        String jsonBody = "{\"invoiceId\":4,\"date\":\"2021-12-24\",\"price\":500,\"table_number\":4}";
        System.out.println("\n\n" + jsonBody);
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
        System.out.println(headers);
        //POST Method to Add New Product
        response = restTemplate. postForEntity(URI, entity, String.class);
    }

    @Then("The status code for Invoice API is {int}")
    public void theStatusCodeForInvoiceAPIis(int statusCode) {
        assertThat(response.getStatusCodeValue()).isEqualTo(statusCode);
    }

    //GET
    @When("User send a GET HTTP request for Invoice API")
    public void userSendAGETHTTPRequestForInvoiceAPI() {
        restTemplate = new RestTemplate();
        response = restTemplate.getForEntity(URI, String.class);
    }

    //PUT
    @And("User send a PUT HTTP request for Invoice API")
    public void userSendAPUTHTTPRequestForInvoiceAPI() {
        URI = URI + "/1?name=Ham";
        response = restTemplate.exchange(URI, HttpMethod.PUT, null, String.class);
    }

    //DELETE
    @Given("User set DELETE invoice service api endpoint")
    public void userSetDELETEInvoiceServiceApiEndpoint() {
        URI = URI + "/1";
        System.out.println(URI);
    }

    @When("User send a DELETE HTTP request for Invoice API")
    public void userSendADELETEHTTPRequestForInvoiceAPI() {
        restTemplate = new RestTemplate();
        response = restTemplate.exchange(URI, HttpMethod.DELETE, null, String.class);
    }

}
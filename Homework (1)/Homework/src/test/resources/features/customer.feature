Feature: Customer Api

  Scenario: Create Customer
    Given User set POST customer service api endpoint
    When User Set request HEADER for Customer API
    And User send a POST HTTP request for Customer API
    Then The status code for Customer API is 200

  Scenario:Get Customers
    When User send a GET HTTP request for Customer API
    Then The status code for Customer API is 200

  Scenario:Update Customer
    When User Set request HEADER for Customer API
    And User send a PUT HTTP request for Customer API
    Then The status code for Customer API is 200

  Scenario:Delete Customer
    Given User set DELETE customer service api endpoint
    When User send a DELETE HTTP request for Customer API
    Then The status code for Customer API is 200
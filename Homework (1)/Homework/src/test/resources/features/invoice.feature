Feature: Invoice Api

  Scenario: Create Invoice
    Given User set POST invoice service api endpoint
    When User Set request HEADER for Invoice API
    And User send a POST HTTP request for Invoice API
    Then The status code for Invoice API is 200

  Scenario:Get Invoices
    When User send a GET HTTP request for Invoice API
    Then The status code for Invoice API is 200

  Scenario:Update Invoice
    When User Set request HEADER for Invoice API
    And User send a PUT HTTP request for Invoice API
    Then The status code for Invoice API is 200

  Scenario:Delete Invoice
    Given User set DELETE invoice service api endpoint
    When User send a DELETE HTTP request for Invoice API
    Then The status code for Invoice API is 200
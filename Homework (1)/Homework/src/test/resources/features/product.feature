Feature: Product Api

  Scenario: Create Product
    Given User set POST product service api endpoint
    When User Set request HEADER for Product API
    And User send a POST HTTP request for Product API
    Then The status code for Product API is 200

  Scenario:Get Products
    When User send a GET HTTP request for Product API
    Then The status code for Product API is 200

  Scenario:Update Product
    When User Set request HEADER for Product API
    And User send a PUT HTTP request for Product API
    Then The status code for Product API is 200

  Scenario:Delete Product
    Given User set DELETE product service api endpoint
    When User send a DELETE HTTP request for Product API
    Then The status code for Product API is 200
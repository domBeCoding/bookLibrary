Feature: book endpoints function properly

  Scenario: /all endpoint polled, status 200 and OK is returned
    When "/books/all" endpoint is polled
    Then status of 200 is returned
    And body of "OK" is returned
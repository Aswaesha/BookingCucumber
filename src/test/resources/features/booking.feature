Feature: Search hotel at Booking
  Scenario: Search hotel in Gomel
    Given Keyword for search is "Gomel"
    When User does search
    Then Hotel "Chisto Hotel" is on the first page
    And Rating should be "9.1"
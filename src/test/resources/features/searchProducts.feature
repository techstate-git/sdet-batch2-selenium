Feature: Search Products on eBay

  Scenario: Search for a product
    Given I am on the eBay homepage
    When I search for "smartphone"
    Then I should see search results containing "smartphone"

  Scenario: Filter search results by price range
    Given I am on the eBay homepage
    When I search for "laptop"
    And I apply a price filter from "300" to "1000"
    Then I should see search results within the price range
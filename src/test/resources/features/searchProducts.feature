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

  Scenario: Sort search results by price (low to high)
    Given I am on the eBay homepage
    When I search for "headphones"
    And I sort the results by "Price + Shipping: lowest first"
    Then I should see results sorted from lowest to highest price

  Scenario: Search and navigate to a specific category
    Given I am on the eBay homepage
    When I search for "sneakers"
    And I filter the results by category "Men"
    And I filter the results by Brand "Jordan"
    Then I should verify that results contains brand "Jordan" on each item











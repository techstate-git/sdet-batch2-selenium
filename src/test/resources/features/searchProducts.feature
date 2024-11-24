Feature: Search Products on eBay

  Scenario: Search for a product
    Given I am on the eBay homepage
    When I search for "smartphone"
    Then I should see search results containing "smartphone"
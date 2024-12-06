Feature: Advance search

  Scenario: Validate search functionality
    Given I am on the eBay homepage
    When I type a search term and verify results
      | laptop |
      | phone  |
      | camera |

  Scenario: Verify page titles
    Given I am on the eBay homepage
    When I navigate to the following pages and print each page has a title
      | Deals       |
      | Electronics |
      | Fashion     |

  Scenario: Validate section headers are present
    Given I am on the eBay homepage
    When I check the following popular categories are displayed
      | Luxury   |
      | Sneakers |
      | Macbook  |
      | Toys     |
      | World    |












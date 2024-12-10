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

  Scenario: Validate gift-cards benefits title
    Given I am on the eBay homepage
    Then I navigate to the Gift Cards page
    When I check the benefits are the same as my examples
      | Hassle-free          |
      | Heartwarming designs |
      | The choice is theirs |

  Scenario Outline: Validate product count on Deals page
    Given I am on the eBay homepage
    When I navigate to the Deals section
    And I click on the "<category>" category
    Then I should verify that at least "<minCount>" products are displayed

    Examples:
      | category      | minCount | maxCount |
      | Collectibles  | 10       | 200      |
      | Fashion       | 200      | 300      |
      | Home & Garden | 15       | 400      |











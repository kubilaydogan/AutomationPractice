@shopping @sauce
Feature: Shopping Cart Functionality
"""
  Test flow:
   	Log into the site
   	Sort the items
   	Add two or more items to the shopping cart
   	Visit the shopping cart
        • Assert that the items that you added are in the cart
   	Remove an item and then continue shopping
   	Add another item
   	Checkout
         • Assert you are purchasing the correct items
         • Assert the total price
         • Finish checkout•
"""

  Scenario Outline: Validate shopping cart
    Given user is on landing page
    When user log into the site
      | username | standard_user |
      | password | secret_sauce  |
    And user sort the items by "<sort_type>"
    And user add "<item1>" to shopping cart
    And user add following items to shopping cart
      | item    |
      | <item2> |
      | <item3> |
    And user add following items to cart
      | <item4> |
      | <item5> |
    And user navigates to the shopping cart
    Then verify that the added items are present in the cart
      | <item1> |
      | <item2> |
      | <item3> |
      | <item4> |
      | <item5> |
    When user removes "<item3>" from shopping cart
    And clicks on "Continue Shopping"
    And user navigates to the shopping cart
    And clicks on "Checkout"
    And user enters checkout information
      | firstName | random |
      | lastName  | Dogan  |
      | zipCode   | random |
    Then verify total sum in checkout overview

    Examples:
      | sort_type           | item1                   | item2               | item3                 | item4                    | item5             |
      | Price (high to low) | Sauce Labs Bolt T-Shirt | Sauce Labs Backpack | Sauce Labs Bike Light | Sauce Labs Fleece Jacket | Sauce Labs Onesie |

  @reset
  Scenario: Verify remove button works
    Given user is on landing page
    When user log into the site and resets app state
      | username | standard_user |
      | password | secret_sauce  |
    And user adds 1st item to shopping cart
    And user adds 3rd item to shopping cart
    And user removes 1st item from shopping cart
    Then verify shopping cart contains 1 item
    And wait for 3 seconds (for demo)

  Scenario: Verify that 'Price Low to High' filter shows the lowest price item first
    Given the user logins with valid credentials
    When user sort the items by "Price (low to high)"
    Then verify that the item with lowest price is displayed first

  @ignore
  Scenario: Verify remove button works 2
    Given cache is cleared
    And user is on landing page
    When user log into the site
      | username | standard_user |
      | password | secret_sauce  |
    And user adds 1st item to shopping cart
    And user adds 3rd item to shopping cart
    And user removes 1st item from shopping cart
    Then verify shopping cart contains 1 item
    And wait for 3 seconds (for demo)



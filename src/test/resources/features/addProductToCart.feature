Feature: Adding product to the cart
  the user should be able to add product to cart
  Scenario Outline: User Add products to cart
    Given User on vodafone shop home page "https://eshop.vodafone.com.eg/shop/home"
    When  The user add product number <productNumberX> to the cart
    And The user continue shopping and add product number <productNumberY> to the cart
    And The user search for "<searchTerm>" and add it to the cart
    Then The three products should existed on the cart
    Examples:
      |productNumberX  | productNumberY  | searchTerm |
      |1  | 2                            | redmi     |

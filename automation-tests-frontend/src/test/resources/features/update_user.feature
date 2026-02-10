@users @updateUser
Feature: Update User Phone

    As an admin
    I want to update the phone number of a user
    So that user contact information remains accurate

  @updateUser, @positive, @req-UL-UPDATE-001
  Scenario Outline: REQ-UL-UPDATE-001 - Update phone modal is shown
    Given user opens the application on "<browser>"
    When user clicks the Update action for a specific user
    Then Update User modal should be displayed
    And all fields except Phone should be disabled

    Examples:
      | browser |
      | chrome  |
      | firefox |

  @updateUser, @positive, @req-UL-UPDATE-002  
  Scenario Outline: REQ-UL-UPDATE-002 - Successfully update phone
    Given user opens the application on "<browser>"
    When user clicks the Update action for a specific user
    And user edits the Phone field with valid data
    And user clicks Update Phone
    Then updated phone should be visible in the Users List

    Examples:
      | browser |
      | chrome  |
      | firefox |

  @updateUser, @negative, @req-UL-UPDATE-003
  Scenario Outline: REQ-UL-UPDATE-004 - Validation error for phone less than 10 digits
    Given user opens the application on "<browser>"
    When user clicks the Update action for a specific user
    And user enters phone number with less than 10 digits
    Then error message below Phone field should be displayed
    And Update Phone button should be disabled

    Examples:
      | browser |
      | chrome  |
      | firefox |

  @updateUser, @debug, @req-UL-UPDATE-004
  Scenario Outline: REQ-UL-UPDATE-003 - Intentional failure for debugging
    Given user opens the application on "<browser>"
    When user clicks the Update action for a specific user
    And user enters invalid phone data
    Then modal should close

    Examples:
      | browser |
      | chrome  |
      | firefox |
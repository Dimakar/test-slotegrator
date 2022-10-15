Feature: Player Table

  Scenario: Open player list
    Given "admin1" "[9k<k8^z!+$$GkuP" is authorized
    When Click "Users" -> "Players" on the sidebar
    Then Player list is visible

  Scenario: Sorting player list by Registration Date asc
    Given "admin1" "[9k<k8^z!+$$GkuP" is authorized
    And Players Page is opened
    When Select sorting by "Registration date"
    Then Sorting by registration date is "ASC"

  Scenario: Sorting player list by Registration Date desc
    Given "admin1" "[9k<k8^z!+$$GkuP" is authorized
    And Players Page is opened
    When Select sorting by "Registration date"
    And Select sorting by "Registration date"
    Then Sorting by registration date is "DESC"




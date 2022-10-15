Feature: Authorization

  Scenario: Admin authorization
    Given Open login page
    When Input "admin1" in Login Field
    And Input "[9k<k8^z!+$$GkuP" in Password Field
    And Click Submit Button
    Then "admin1" authorized successfully
    And Admin panel is opened




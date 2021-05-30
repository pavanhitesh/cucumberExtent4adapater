@Chrome1
Feature: Sample test case to open selenium dev application in new parallel browser

  Scenario: To open selenium dev application and navigate to documents
    Given User navigate to url "https://www.selenium.dev/"
    When User navigate to documents
    Then user sucessfully navigated to documents

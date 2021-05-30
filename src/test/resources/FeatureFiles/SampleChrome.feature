@Chrome
Feature: Sample test case to open selenium dev application

  Scenario: To open selenium dev application and navigate to projects
    Given User navigate to url "https://www.selenium.dev/"
    When User navigate to projects
    Then user sucessfully navigated to projects

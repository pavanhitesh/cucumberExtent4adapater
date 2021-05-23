@Covid
Feature: To Validate the Covid Information from API

  Scenario: To Get the latest covid details from the open source api
    Given user configured the api with details from the properties file "api-covid"
    When user get the latest covid information from endpoint "/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true"
    Then validate the response with following fields displayed
    |activeCases|
    |activeCasesNew|
    |recovered|
    |recoveredNew|
    |totalCases  |
    |lastUpdatedAtApify|
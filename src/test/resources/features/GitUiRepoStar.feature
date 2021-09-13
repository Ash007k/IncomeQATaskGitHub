@regression @star
Feature: To Validate and Compare GitHub repo for given users Stars with Backend API

  Background: Open Application in required browser
    Given Open Application

  @star
  Scenario Outline: To validate Stars are showing in K format if round of value is more than or equal to 1000
    When Search the "<userName>" user
    And  Navigate to "<userName>" users repository page
    And Search and Open "<repoName>" repository
    Then Get and store Number of Stars for current repo
    And Call and store GitHub API Star value for "<userName>" user and "<repoName>"
    And the both UI star and API should match with K - "<formatInK>"
    Examples:
      | userName | repoName    | formatInK |
      | torvalds | linux       | true      |
      | ash007ok | cicd-images | false     |

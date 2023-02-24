@speer
Feature: QA Assessment

  Scenario Outline: Retrieving all embedded wikipedia links in a page
    Given user is on a "<wikipedia_link>"
    When all embedded wikipedia links in a page are stored in a list
    And repeat previous step for <n> times for newly found links
    And write the links in a json file

    Examples:
      | wikipedia_link                        | n |
      | https://en.wikipedia.org/wiki/Quality | 3 |
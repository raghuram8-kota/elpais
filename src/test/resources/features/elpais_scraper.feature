@run
Feature: El Pais Opinion Section Scraping

  @android @ios
  Scenario: Ensure that the website's text is displayed in Spanish
    When I navigate to the ElPais page
    Then I verify the page loads in spanish

  @chrome @firefox @edge @android @ios
  Scenario: Fetch titles and content of top 5 opinion articles
    Given I navigate to the ElPais "opinion" section
    When I fetch the top 5 articles
    Then I print the title and content of each article in Spanish
    And I download article images to my local

    When I translate the article titles to English
    Then I print the words repeated from more than 1 instance across all headers combined

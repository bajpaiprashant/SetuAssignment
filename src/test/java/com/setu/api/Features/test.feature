Feature: Verify creating a Jira issue using APIs

  @registration @sanity
  Scenario: Create a Issue in the Jira Project
    Given I set endpoint as "<--config.properties/HOST_URL" with endpoint "/issue"
    * I authenticate with username "<--config.properties/USERNAME" and password "<--config.properties/TOKEN"
    Given I use json template "/data/payload/createIssue.json"
    When  I mention "$..key" as "<--config.properties/PROJECT_NAME"
    When  I mention "$..summary" as "$$rndString"
    Then I print request
    *    I invoke the POST API with json payload
    Then I print response
    Then I verify the status code to be "201"
    Then I save response to "createIssueResponse.text/name"
    Then I verify "$..key" contains text "TES"
    Then I verify "$..id" is not NULL
    Then I save value "$..id-->config.properties/id"


  @registration @sanity
  Scenario: Adding an attachment to the Jira Issue
    Given I set endpoint as "<--config.properties/HOST_URL" with endpoint "/issue/" along with "<--config.properties/id" and "/attachments"
    Then I add request header "X-Atlassian-Token" as "no-check"
    * I authenticate with username "<--config.properties/USERNAME" and password "<--config.properties/TOKEN"
    Then I print request
    *    I invoke the POST API with multipart payload "/data/payload/createIssue.json" and "createIssueResponse.text"
    Then I print response
    Then I verify the status code to be "200"
    Then I verify "$..filename" contains text "createIssueResponse.text"
    Then I verify "$..id" is not NULL



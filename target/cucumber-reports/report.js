$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/com/setu/api/Features/test.feature");
formatter.feature({
  "line": 1,
  "name": "Verify creating a Jira issue using APIs",
  "description": "",
  "id": "verify-creating-a-jira-issue-using-apis",
  "keyword": "Feature"
});
formatter.before({
  "duration": 227126,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Create a Issue in the Jira Project",
  "description": "",
  "id": "verify-creating-a-jira-issue-using-apis;create-a-issue-in-the-jira-project",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@registration"
    },
    {
      "line": 3,
      "name": "@sanity"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I set endpoint as \"\u003c--config.properties/HOST_URL\" with endpoint \"/issue\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I authenticate with username \"\u003c--config.properties/USERNAME\" and password \"\u003c--config.properties/TOKEN\"",
  "keyword": "* "
});
formatter.step({
  "line": 7,
  "name": "I use json template \"/data/payload/createIssue.json\"",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I mention \"$..key\" as \"\u003c--config.properties/PROJECT_NAME\"",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "I mention \"$..summary\" as \"$$rndString\"",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "I print request",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "I invoke the POST API with json payload",
  "keyword": "* "
});
formatter.step({
  "line": 12,
  "name": "I print response",
  "keyword": "Then "
});
formatter.step({
  "line": 13,
  "name": "I verify the status code to be \"201\"",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "I save response to \"createIssueResponse.text/name\"",
  "keyword": "Then "
});
formatter.step({
  "line": 15,
  "name": "I verify \"$..key\" contains text \"TES\"",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "I verify \"$..id\" is not NULL",
  "keyword": "Then "
});
formatter.step({
  "line": 17,
  "name": "I save value \"$..id--\u003econfig.properties/id\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "\u003c--config.properties/HOST_URL",
      "offset": 19
    },
    {
      "val": "/issue",
      "offset": 65
    }
  ],
  "location": "StepDefCore.setEndpoint(String,String)"
});
formatter.result({
  "duration": 791140557,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "\u003c--config.properties/USERNAME",
      "offset": 30
    },
    {
      "val": "\u003c--config.properties/TOKEN",
      "offset": 75
    }
  ],
  "location": "StepDefCore.basicAuth(String,String)"
});
formatter.result({
  "duration": 979873684,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/data/payload/createIssue.json",
      "offset": 21
    }
  ],
  "location": "StepDefCore.loadTemplateJSON(String)"
});
formatter.result({
  "duration": 4663166,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "$..key",
      "offset": 11
    },
    {
      "val": "\u003c--config.properties/PROJECT_NAME",
      "offset": 23
    }
  ],
  "location": "StepDefJson.editJSON(String,String)"
});
formatter.result({
  "duration": 341347089,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "$..summary",
      "offset": 11
    },
    {
      "val": "$$rndString",
      "offset": 27
    }
  ],
  "location": "StepDefJson.editJSON(String,String)"
});
formatter.result({
  "duration": 3488163,
  "status": "passed"
});
formatter.match({
  "location": "StepDefCore.printFullRequest()"
});
formatter.write("Request:{\"fields\":{\"project\":{\"key\":\"TES\"},\"summary\":\"oawxkamz\",\"issuetype\":{\"name\":\"Task\"}}}");
formatter.result({
  "duration": 1925850,
  "status": "passed"
});
formatter.match({
  "location": "StepDefCore.invokePostAPIWithPayload()"
});
formatter.result({
  "duration": 4890120915,
  "status": "passed"
});
formatter.match({
  "location": "StepDefCore.printFullResponse()"
});
formatter.write("Response:{\"id\":\"10043\",\"key\":\"TES-38\",\"self\":\"https://bajpaiprashant.atlassian.net/rest/api/3/issue/10043\"}");
formatter.result({
  "duration": 863942,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "201",
      "offset": 32
    }
  ],
  "location": "StepDefCore.verifyStatusCode(int)"
});
formatter.write("Status Code:201");
formatter.result({
  "duration": 155543547,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "createIssueResponse.text/name",
      "offset": 20
    }
  ],
  "location": "StepDefCore.saveFullResponse(String)"
});
formatter.result({
  "duration": 1580694,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "$..key",
      "offset": 10
    },
    {
      "val": "TES",
      "offset": 33
    }
  ],
  "location": "StepDefJson.readValueFromResponseforSubString(String,String)"
});
formatter.result({
  "duration": 13024672,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "$..id",
      "offset": 10
    }
  ],
  "location": "StepDefJson.readValueFromResponseForNotNull(String)"
});
formatter.result({
  "duration": 4846792,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "$..id--\u003econfig.properties/id",
      "offset": 14
    }
  ],
  "location": "StepDefJson.saveElement(String)"
});
formatter.result({
  "duration": 22147569,
  "status": "passed"
});
formatter.after({
  "duration": 234687,
  "status": "passed"
});
formatter.before({
  "duration": 42614,
  "status": "passed"
});
formatter.scenario({
  "line": 21,
  "name": "Adding an attachment to the Jira Issue",
  "description": "",
  "id": "verify-creating-a-jira-issue-using-apis;adding-an-attachment-to-the-jira-issue",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 20,
      "name": "@registration"
    },
    {
      "line": 20,
      "name": "@sanity"
    }
  ]
});
formatter.step({
  "line": 22,
  "name": "I set endpoint as \"\u003c--config.properties/HOST_URL\" with endpoint \"/issue/\" along with \"\u003c--config.properties/id\" and \"/attachments\"",
  "keyword": "Given "
});
formatter.step({
  "line": 23,
  "name": "I add request header \"X-Atlassian-Token\" as \"no-check\"",
  "keyword": "Then "
});
formatter.step({
  "line": 24,
  "name": "I authenticate with username \"\u003c--config.properties/USERNAME\" and password \"\u003c--config.properties/TOKEN\"",
  "keyword": "* "
});
formatter.step({
  "line": 25,
  "name": "I print request",
  "keyword": "Then "
});
formatter.step({
  "line": 26,
  "name": "I invoke the POST API with multipart payload \"/data/payload/createIssue.json\" and \"createIssueResponse.text\"",
  "keyword": "* "
});
formatter.step({
  "line": 27,
  "name": "I print response",
  "keyword": "Then "
});
formatter.step({
  "line": 28,
  "name": "I verify the status code to be \"200\"",
  "keyword": "Then "
});
formatter.step({
  "line": 29,
  "name": "I verify \"$..filename\" contains text \"createIssueResponse.text\"",
  "keyword": "Then "
});
formatter.step({
  "line": 30,
  "name": "I verify \"$..id\" is not NULL",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "\u003c--config.properties/HOST_URL",
      "offset": 19
    },
    {
      "val": "/issue/",
      "offset": 65
    },
    {
      "val": "\u003c--config.properties/id",
      "offset": 86
    },
    {
      "val": "/attachments",
      "offset": 116
    }
  ],
  "location": "StepDefCore.setEndpointAppID(String,String,String,String)"
});
formatter.result({
  "duration": 14592824,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "X-Atlassian-Token",
      "offset": 22
    },
    {
      "val": "no-check",
      "offset": 45
    }
  ],
  "location": "StepDefCore.addRequestHeaders(String,String)"
});
formatter.result({
  "duration": 646280,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "\u003c--config.properties/USERNAME",
      "offset": 30
    },
    {
      "val": "\u003c--config.properties/TOKEN",
      "offset": 75
    }
  ],
  "location": "StepDefCore.basicAuth(String,String)"
});
formatter.result({
  "duration": 674498,
  "status": "passed"
});
formatter.match({
  "location": "StepDefCore.printFullRequest()"
});
formatter.write("Request:{\"fields\":{\"project\":{\"key\":\"TES\"},\"summary\":\"oawxkamz\",\"issuetype\":{\"name\":\"Task\"}}}");
formatter.result({
  "duration": 274400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "/data/payload/createIssue.json",
      "offset": 46
    },
    {
      "val": "createIssueResponse.text",
      "offset": 83
    }
  ],
  "location": "StepDefCore.invokeAPIwithMultiPartPayload(String,String)"
});
formatter.result({
  "duration": 1533991171,
  "status": "passed"
});
formatter.match({
  "location": "StepDefCore.printFullResponse()"
});
formatter.write("Response:[{\"self\":\"https://bajpaiprashant.atlassian.net/rest/api/3/attachment/10004\",\"id\":\"10004\",\"filename\":\"createIssueResponse.text\",\"author\":{\"self\":\"https://bajpaiprashant.atlassian.net/rest/api/3/user?accountId\u003d5fdb5f70692b790110528752\",\"accountId\":\"5fdb5f70692b790110528752\",\"emailAddress\":\"bajpaiprashant21@gmail.com\",\"avatarUrls\":{\"48x48\":\"https://secure.gravatar.com/avatar/73fe9d2a7c168383190465d3e8434f64?d\u003dhttps%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FPB-1.png\",\"24x24\":\"https://secure.gravatar.com/avatar/73fe9d2a7c168383190465d3e8434f64?d\u003dhttps%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FPB-1.png\",\"16x16\":\"https://secure.gravatar.com/avatar/73fe9d2a7c168383190465d3e8434f64?d\u003dhttps%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FPB-1.png\",\"32x32\":\"https://secure.gravatar.com/avatar/73fe9d2a7c168383190465d3e8434f64?d\u003dhttps%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FPB-1.png\"},\"displayName\":\"Prashant Bajpai\",\"active\":true,\"timeZone\":\"Asia/Calcutta\",\"accountType\":\"atlassian\"},\"created\":\"2021-01-23T16:30:04.725+0530\",\"size\":147,\"mimeType\":\"file\",\"content\":\"https://bajpaiprashant.atlassian.net/secure/attachment/10004/createIssueResponse.text\"}]");
formatter.result({
  "duration": 613493,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 32
    }
  ],
  "location": "StepDefCore.verifyStatusCode(int)"
});
formatter.write("Status Code:200");
formatter.result({
  "duration": 2618905,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "$..filename",
      "offset": 10
    },
    {
      "val": "createIssueResponse.text",
      "offset": 38
    }
  ],
  "location": "StepDefJson.readValueFromResponseforSubString(String,String)"
});
formatter.result({
  "duration": 972719,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "$..id",
      "offset": 10
    }
  ],
  "location": "StepDefJson.readValueFromResponseForNotNull(String)"
});
formatter.result({
  "duration": 796699,
  "status": "passed"
});
formatter.after({
  "duration": 155690,
  "status": "passed"
});
});
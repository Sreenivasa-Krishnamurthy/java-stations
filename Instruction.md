Java-Stations-Test
===================


## Instructions

1. Please read this document carefully
2. Fork the candidates/java-stations-test.git  public git repository
3. Read the README.md file containing the stories and acceptance tests 
4. Create your local branch on your forked version and call the local branch as name_surname
5. Push it to your forked reposirtory wih the commit comment *"Test started"*
6. While you are developing commit as more often as you can.
7. Provide tests in your preferred style to support your solution.  
The application has to be developed using spring framework, JPA, H2 as database and Jetty as container. 
Apart from that, you can use your own technology stack for testing and implementation.
8. Push to your remote branch 
9. When you are done please rename the README.md to INSTRUCTION.md and create your README.md with the general instruction of how to run it.
10. THen please commit using the comment *"Test completed"*
11. The user stories are just instructions, if you want you can refactor them as you wish, including the acceptance tests.

## NOTE(1): We won't review any test that doesn't have the *Test started* and *Test completed* commit comments in the git log.

## NOTE(2): DON'T PUSH TO THE ORIGINAL MASTER


Feature:
As a user I want to be able to search for train stations by train station name
  
  
      Background:
        And the following train stations exist:
        |name                 |
        |DARTFORD             |
        |DARTMOUTH            |
        |TOWER HILL           |
        |DERBY                |
        |LIVERPOOL            |
        |LIVERPOOL LIME STREET|
        |PADDINGTON           |
        |EUSTON               |
        |LONDON BRIDGE        |
        |VICTORIA             |



  Scenario: Search for DART
  
        Given the service is up an running
        And I am on a RESTFull client screen
        When I enter the train station name 'DART'
        Then the response code should be 200
        And the search should return a JSON response as following:
          {
            "stations": [
              {
                "name": "DARTFORD"
              },
              {
                "name": "DARTMOUTH"
              }
            ]
          }


  Scenario: Search for LIVERPOOL
  
        Given the service is up an running
        And I am on a RESTFull client screen
        When I enter the train station name 'LIVERPOOL'
        Then the response code should be 200
        And the search should return a JSON response as following:
              {
                "stations": [
                  {
                    "name": "LIVERPOOL"
                  },
                  {
                    "name": "LIVERPOOL LIME STREET"
                  }
                ]
              }
          
   Scenario: Search for DERBY
   
        Given the service is up an running
        And I am on a RESTFull client screen
        When I enter the train station name 'DERBY'
        Then the response code should be 200
        And the search should return a JSON response containing the 'DEBY' station
             
  Scenario: Search for KINGS CROSS
  
      Given the service is up an running
      And I am on a RESTFull client screen
      When the input 'KINGS CROSS'
      Then the search should return no characters and no stations
   
  Scenario: Search for All
  
     Given the service is up an running
     And I am on a RESTFull client screen
     When I call the searchAll API
     Then the response code should be 200
     And the search should return all the stations available in a JSON format
  
     
  Scenario: api doent's exist
  
       Given the service is up an running
       And I am on a RESTFull client screen
       When I call the searchAllThatDoesNotExist API
       Then the response code should be 404
       
   Scenario: check health of the service
   
          Given the service is up an running
          And I am on a RESTFull client screen
          When I call the health API
          Then the response code should be 200
          And the response body should contain a JSON message telling that the service is UP
     

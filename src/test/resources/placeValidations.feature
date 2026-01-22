Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When  user calls "AddPlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "GetPlaceAPI" 
	
Examples:
	|name		|	language	|	address				|
	|AAhouse	|	English		|	World cross center	|
	|BBhouse	|	Hebrew		|	Sea cross center	|

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
	Given  DeletePlace Pyload
	When  user calls "DeletePlaceAPI" with "Delete" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
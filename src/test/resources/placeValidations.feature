Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When  user calls "AddPlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And I record the "place_id" from the response
	And "scope" in response body is "APP"
	And verify "name" maps to "<name>" using "GetPlaceAPI"
	
Examples:
	|name		|	language	|	address				|
	|AAhouse	|	English		|	World cross center	|
	|BBhouse	|	Hebrew		|	Sea cross center	|
	
@EditPlace @Regression
Scenario Outline: Verify if Edit Place functionality is working
	Given  EditPlace Payload with "<address>"
	When  user calls "UpdatePlaceAPI" with "PUT" http request
	Then the API call got success with status code 200
	And "msg" in response body is "Address successfully updated"
	And verify "address" maps to "<address>" using "GetPlaceAPI"
	
Examples:
	|address				|
	|First Edit Location	|
	|Second Edit Location	|

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
	Given  DeletePlace Payload
	When  user calls "DeletePlaceAPI" with "Delete" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
package stepDefinitions;

import static io.restassured.RestAssured.given;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

public class StepDefinition extends Utils {
    
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	JsonPath js;
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}

	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
	
		APIResources resourceAPI = APIResources.valueOf(resource);
		resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("DELETE"))
			response = res.when().delete(resourceAPI.getResource());
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    assertEquals(200, response.getStatusCode());
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(expectedValue, getJsonPath(response,keyValue));
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resourceName) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_post_http_request(resourceName, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(expectedName, actualName);
	}
	
	@Given("DeletePlace Pyload")
	public void delete_place_pyload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
	





}
package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace or @EditPlace")
	public void beforeScenario() throws IOException {
		StepDefinition m = new StepDefinition();
		m.add_place_payload_with("Shoham", "Hebrew", "Israel");
		m.user_calls_with_post_http_request("AddPlaceAPI", "POST");
		m.i_record_the_from_the_response("place_id");
	}
	
	@After("@AddPlace")
	public void afterAddScenario() throws IOException {
		StepDefinition m = new StepDefinition();
		if (StepDefinition.place_id != null) {
			m.delete_place_payload();
			m.user_calls_with_post_http_request("DeletePlaceAPI", "DELETE");
		}
	}
}

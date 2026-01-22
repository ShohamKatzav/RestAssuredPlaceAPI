package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.PostLocation;

public class TestDataBuild {
	
	public PostLocation addPlacePayload(String name, String language, String address) {
		PostLocation pl = new PostLocation();
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(-38.383494);
		pl.setLocation(l);
		pl.setAccuracy(50);
		pl.setName(name);
		pl.setPhone_number("(+972) 523 292 847");
		pl.setAddress(address);
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		pl.setTypes(types);
		pl.setWebsite("https://shoham-katzav-portfolio.vercel.app/");
		pl.setLanguage(language);
		return pl;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n"
				+ "    \"place_id\" : \"" + placeId + "\"\r\n"
				+ "}";
	}

}

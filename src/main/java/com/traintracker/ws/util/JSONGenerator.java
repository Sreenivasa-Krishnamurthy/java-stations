package com.traintracker.ws.util;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.traintracker.ws.domain.Station;

public class JSONGenerator {
	
	private static final String KEY= "name";
	private static final String STATIONS= "stations";
	private static final String STATUS= "Status";
	private static final String SERVICE_UP= "The service is UP";
	
	private JSONGenerator() {}
	
	public static String generateJSONForStations(Iterable<Station> stations) throws IOException  {
		
		JSONArray stationArray = new JSONArray();
		String key = KEY;
		for (Station item :stations) {
			JSONObject stationJo = new JSONObject();
			stationJo.put(key, item.getName());
			stationArray.put(stationJo);
		}
		JSONObject jsonObject =new JSONObject().put(STATIONS, stationArray);
		ObjectMapper mapper = new ObjectMapper();
		Object json = mapper.readValue(jsonObject.toString(), Object.class);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
			
	}

	public static String generateJSONForHealthCheck() throws JsonParseException, JsonMappingException, IOException {
		JSONObject jsObj = new JSONObject();
		jsObj.put(STATUS, SERVICE_UP);
		ObjectMapper mapper = new ObjectMapper();
		Object json = mapper.readValue(jsObj.toString(), Object.class);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
	}
}

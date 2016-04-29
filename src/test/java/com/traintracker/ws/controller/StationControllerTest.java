package com.traintracker.ws.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.traintracker.ws.AbstractControllerTest;
import com.traintracker.ws.domain.Station;
import com.traintracker.ws.repository.StationRepositoryService;


public class StationControllerTest extends AbstractControllerTest {

	@Autowired
	private StationRepositoryService repositoryService;
	
	@Before
	public void setUp() {
		super.setUp();
		
	}
	
	@Test
	public void testSearchForAll() throws Exception {
		String uri = "/api/stations/searchAll";
		
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri).accept(
						MediaType.APPLICATION_JSON)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have content",
				content.trim().length() > 0);
	}
	
	@Test
	public void testSearchForOneEntry() throws Exception {
		String uri = "/api/stations/DERBY";
		
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri).accept(
						MediaType.APPLICATION_JSON)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		List<Station> theStations = parseJSONResponse(content);
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertEquals("failure- expecting one station", 1,theStations.size());
		Assert.assertTrue("failure- expecting DERBY entry", 
				theStations.get(0).getName().equals("DERBY"));
	}
	
	@Test
	public void testSearchForTwoEntriesSceanrio2() throws Exception {
		String uri = "/api/stations/DART";
		
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri).accept(
						MediaType.APPLICATION_JSON)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		List<Station> theStations = parseJSONResponse(content);
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertEquals("failure- expecting two stations", 2,theStations.size());
		Assert.assertTrue("failure- expecting DARTFORD entry", 
				theStations.get(0).getName().contains("DARTFORD"));
	}
	
	@Test
	public void testSearchForTwoEntriesSceanrio1() throws Exception {
		String uri = "/api/stations/LIVERPOOL";
		
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri).accept(
						MediaType.APPLICATION_JSON)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		List<Station> theStations = parseJSONResponse(content);
		
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertEquals("failure- expecting two stations", 2,theStations.size());
		Assert.assertTrue("failure- expecting LIVERPOOL entry", 
				theStations.get(0).getName().contains("LIVERPOOL"));
	}
	
	@Test
	public void testSearchForMissingEntry() throws Exception {
		String uri = "/api/stations/KINGS CROSS";
		
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri).accept(
						MediaType.APPLICATION_JSON)).andReturn();
		
		String content = result.getResponse().getContentAsString();
	
		Assert.assertTrue("Failure - Expecting no content",content.length()==0);
	}
	
	@Test
	public void testMissingApi() throws Exception {
		String uri = "/api/stations/searchAllThatDoesNotExist";
		
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri).accept(
						MediaType.APPLICATION_JSON)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
	
		Assert.assertTrue("Failure - Expecting no content",content.length()==0);
		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	}
	
	@Test
	public void testHealth() throws Exception {
		String uri = "/api/stations/health";
		
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri).accept(
						MediaType.APPLICATION_JSON)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
	
		Assert.assertTrue("Failure - Expecting UP",content.contains("UP"));
		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	}

		
	private List<Station> parseJSONResponse(String content)
			throws JsonParseException, JsonMappingException, IOException {
		JSONObject obj = new JSONObject(content);
		JSONArray arr = obj.getJSONArray("stations");
		
		List<Station> theStations = new ArrayList<Station>();
		
		for (int i =0 ; i< arr.length(); i++) {
			String strStation = arr.getJSONObject(i).toString();
			Station stn = super.mapFromJsonString(strStation, Station.class);
			theStations.add(stn);
		}
		return theStations;
	}
}

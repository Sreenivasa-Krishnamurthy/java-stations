package com.traintracker.ws.controller;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.traintracker.ws.domain.Station;
import com.traintracker.ws.repository.StationRepositoryService;
import com.traintracker.ws.util.JSONGenerator;

/**
 * The StationController class is a RESTFUL web service controller. The
 * <code>@RestController</code> annotation informs Spring that each
 * <code>@RequestMapping</code> method returns a <code>@ResponseBody</code>
 * which by default contains a ResponseEntity converted into JSON with an 
 * associated HTTP status code
 */

@RestController
public class StationController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(StationController.class);
	@Autowired
	private StationRepositoryService stationRepository;
	
	/**
	 * Web service endpoint to fetch all the stations. The service returns
	 * the collection of stations as JSON.
	 * @return ResponseEntity containing the collection of stations
	 * @throws IOException
	 */
	@RequestMapping(
			value="/api/stations/searchAll",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllStations() throws IOException {
		String json= JSONGenerator.generateJSONForStations(stationRepository.findAll());
		log.debug(json);
		return new ResponseEntity<String>((json), HttpStatus.OK);
	}
	
	/**
	 * Web service endpoint to fetch a station/stations matching the word.
	 * The service returns
	 * the collection of station/stations as JSON.
	 * @return ResponseEntity containing the collection of station/stations
	 * @throws IOException
	 */
	@RequestMapping(
			value="/api/stations/{name}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStationNames(@PathVariable("name") String name) throws IOException {
		List<Station> stations = stationRepository.findByNameContaining(name);
		if (stations.size() >0 ) {
			String json= JSONGenerator.generateJSONForStations(stations);
			log.debug(json);
			return new ResponseEntity<String>((json), HttpStatus.OK);
		}
		return new ResponseEntity<String>("",HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/api/stations/searchAllThatDoesNotExist",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> missingApi() {
		return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Web service endpoint for health check.
	 * The service returns
	 * the health check status as JSON.
	 * @return ResponseEntity containing the health check status
	 */
	@RequestMapping(
			value="/api/stations/health",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> healthCheck() throws JsonParseException, JsonMappingException, IOException {
		return new ResponseEntity<String>(JSONGenerator.generateJSONForHealthCheck()
				,HttpStatus.OK);
	}
}

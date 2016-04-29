package com.traintracker.ws.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import com.traintracker.ws.domain.Station;


public class JSONGeneratorTest {

	@Test
	public void testJSONFormatterForStations() throws IOException {
		List<Station> stations = new ArrayList<Station>();
				
		stations.add(new Station("DARTFORD"));
		stations.add(new Station("DARTMOUTH"));
		stations.add(new Station("TOWER HILL"));
		stations.add(new Station("DERBY"));
		stations.add(new Station("LIVERPOOL"));
		stations.add(new Station("LIVERPOOL LIME STREET"));
		stations.add(new Station("PADDINGTON"));
		stations.add(new Station("EUSTON"));
		stations.add(new Station("LONDON BRIDGE"));
		stations.add(new Station("VICTORIA"));
		
		String json = JSONGenerator.generateJSONForStations(stations);
		Assert.assertTrue("failure - expected JSON to have a value",
				json.trim().length() > 0);
	}
	
	@Test
	public void testJSONFormatterForHealthCheck() throws IOException {
		String json = JSONGenerator.generateJSONForHealthCheck();
		Assert.assertTrue("failure - expected JSON to have a value",
				json.trim().length() > 0);
	}

}

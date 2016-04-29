package com.traintracker.ws;

/* Application start up class 
 * 
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.traintracker.ws.domain.Station;
import com.traintracker.ws.repository.StationRepositoryService;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner addRecords(StationRepositoryService repository) {
		return (args) -> {
			repository.save(new Station("DARTFORD"));
			repository.save(new Station("DARTMOUTH"));
			repository.save(new Station("TOWER HILL"));
			repository.save(new Station("DERBY"));
			repository.save(new Station("LIVERPOOL"));
			repository.save(new Station("LIVERPOOL LIME STREET"));
			repository.save(new Station("PADDINGTON"));
			repository.save(new Station("EUSTON"));
			repository.save(new Station("LONDON BRIDGE"));
			repository.save(new Station("VICTORIA"));
			
			for (Station stn : repository.findAll()) {
				log.info(stn.toString());
			}
		};
	}

}

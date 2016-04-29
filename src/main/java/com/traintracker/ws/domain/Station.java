package com.traintracker.ws.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Station {
	
	@Id
	private String name;
	
	protected Station() {}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Station(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Station [Name='%s']", name);
	}
}

package com.traintracker.ws.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.traintracker.ws.domain.Station;

@Service
@Repository
public interface StationRepositoryService extends CrudRepository<Station,String>{

		List<Station> findByNameContaining(@Param("name") String name);
}

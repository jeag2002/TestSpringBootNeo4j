package es.wellat.testNeo4j.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;


import es.wellat.testNeo4j.model.Plate;

public interface PlateRepository extends Neo4jRepository<Plate,Long>{
	Plate findByName(String name);
}

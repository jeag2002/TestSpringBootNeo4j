package es.wellat.testNeo4j.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import es.wellat.testNeo4j.model.Course;

public interface CourseRepository extends Neo4jRepository<Course,Long>{

	Course findByName(String name);
}

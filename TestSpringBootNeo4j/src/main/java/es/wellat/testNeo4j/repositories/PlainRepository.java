package es.wellat.testNeo4j.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import es.wellat.testNeo4j.model.Plain;

@RepositoryRestResource(collectionResourceRel = "plains", path = "plains")
public interface PlainRepository extends Neo4jRepository<Plain, Long>{
	
	Plain findByName(@Param("name") String name);
	
	List<Plain> findAll();
	
    //@Query("MATCH (p:Plain)<-[m:MEAL]-(c:Course)<-[d:DISH]-(l:Plate) RETURN p,m,c,d,l LIMIT 100")
	@Query("MATCH (p:Plain)-[m:MEAL]-(c:Course)-[d:DISH]-(l:Plate) RETURN * LIMIT 100")
	Collection<Plain> graph(@Param("limit") int limit);
	
	//FILTER of COURSE
	//neo4j-v3
	//@Query("MATCH (p:Plain)-[m:MEAL]-(c:Course)-[d:DISH]-(l:Plate) WHERE c.type = {course}  RETURN * LIMIT 100")
	//neo4j-v4 (docker)
	@Query("MATCH (p:Plain)-[m:MEAL]-(c:Course)-[d:DISH]-(l:Plate) WHERE c.type = $course  RETURN * LIMIT 100")
	Collection<Plain> graphCourse(@Param("course") String courseType);
	
	//FILTER of NO INGREDIENT
	//FILTER of COURSE AND NO INGREDIENT
    //neo4j-v3
	//@Query("MATCH (p:Plain)-[m:MEAL]-(c:Course)-[d:DISH]-(l:Plate) WHERE not exists((p)-[:MEAL]-(c)-[:DISH]-(:Plate {name: {ingredient}}))  RETURN * LIMIT 100 ")
    //neo4j-v4 (docker)
	@Query("MATCH (p:Plain)-[m:MEAL]-(c:Course)-[d:DISH]-(l:Plate) WHERE not exists((p)-[:MEAL]-(c)-[:DISH]-(:Plate {name: $ingredient}))  RETURN * LIMIT 100 ")
	Collection<Plain> graphNoIngredient(@Param("ingredient")String ingredient);
	
	//FILTER of COURSE AND NO INGREDIENT
	//neo4j-v3
	//@Query("MATCH (p:Plain)-[m:MEAL]-(c:Course)-[d:DISH]-(l:Plate) WHERE c.type = {course} and not exists((p)-[:MEAL]-(c)-[:DISH]-(:Plate {name: {ingredient}}))  RETURN * LIMIT 100 ")
	//neo4j-v4 (docker)
	@Query("MATCH (p:Plain)-[m:MEAL]-(c:Course)-[d:DISH]-(l:Plate) WHERE c.type = $course and not exists((p)-[:MEAL]-(c)-[:DISH]-(:Plate {name: $ingredient}))  RETURN * LIMIT 100 ")
	Collection<Plain> graphCourseNoIngredient(@Param("course")String courseType, @Param("ingredient")String ingredient);
	
	
	

}

package es.wellat.testNeo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("es.wellat.testNeo4j.repositories")
public class SpringBootNeo4jApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootNeo4jApplication.class, args);
	}

}

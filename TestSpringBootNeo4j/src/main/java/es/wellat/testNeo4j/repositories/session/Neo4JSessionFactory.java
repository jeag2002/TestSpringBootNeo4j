package es.wellat.testNeo4j.repositories.session;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class Neo4JSessionFactory {

	  private static ClasspathConfigurationSource props = new ClasspathConfigurationSource("app.properties");
	  private static Configuration configuration = new Configuration.Builder(props).build();

	  private static SessionFactory sessionFactory = new SessionFactory(configuration , "es.wellat.testNeo4j.model");
	  private static Neo4JSessionFactory factory = new Neo4JSessionFactory();

	  public static Neo4JSessionFactory getInstance() {
	    return factory;
	  }

	  private Neo4JSessionFactory() {
	  }

	  public Session getNeo4jSession() {
	    return sessionFactory.openSession();
	  }
}
package es.wellat.testNeo4j.repositories.session;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.wellat.testNeo4j.model.Plain;


@Repository
public class QuerySession {
	
	Logger log = LoggerFactory.getLogger(QuerySession.class);

	
	public List<Plain> courses(List<String> courses) {
		
		
		log.info("[QUERY SESSION] GET COURSES WITH MORE THAN ONE CRITERIA!");
		
		List<Plain> plain = new ArrayList<Plain>();
		
		
		try {
			
			Session session = Neo4JSessionFactory.getInstance().getNeo4jSession();
				
			String cypher = "MATCH (p:Plain)-[m:MEAL]-(c:Course)-[d:DISH]-(l:Plate) WHERE {0}  RETURN * LIMIT 100";
			String data = "c.type = '" +  courses.get(0) + "'";
			
			for(int i=1; i<courses.size(); i++) {
				data += " OR c.type = '" + courses.get(i) + "'";
			}
			
			cypher = MessageFormat.format(cypher, data);
			
			log.info("[QUERY SESSION] launch QUERY <" + cypher + ">");
			
			Result res = session.query(cypher, new HashMap<String,String>());
			
			
			Iterator<Map<String,Object>> it = res.iterator();
			
			int row_index = 1;
			
			while(it.hasNext()) {
		        Map<String, Object> row = it.next();
		        
		        for(Map.Entry<String, Object> col : row.entrySet()) {
		            log.info("[QUERY SESSION] row[" + row_index + "] " +  col.getKey() + " " + col.getValue());   
		        }
		        
		        row_index++;
		    }
			
			
		}catch(Exception e) {
			
			log.error("[QUERY SESSION] ERROR " +  e);
		}
		
		
		return plain;
		
		
		
	}
	
	

}

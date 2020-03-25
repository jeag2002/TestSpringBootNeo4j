package es.wellat.testNeo4j.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Un plato. (Deberia haber una subdivisión por ingredientes. No se pone por simplificar) 
 * @author jeag2
 *
 */

@NodeEntity
public class Plate {
	
    @Id
    @GeneratedValue
    private Long id;
    
	private String name;
    
	@Relationship(type = "DISH")
	private List<Course> courses = new ArrayList<>();
    
	public Plate() {
	}
	 
	public Plate(String name) {
		 this.name = name;
	}
	 
	public List<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		  if (this.courses == null) {
			  this.courses = new ArrayList<>();
		  }
		 this.courses.add(course);
	}
	
	public Long getId() {
			return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}

package es.wellat.testNeo4j.model;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;


/**
 * Comidas. Relacion entre Plain <--> Courses
 * @author jeag2
 */

@RelationshipEntity(type = "MEAL")
public class Meal {
	
    @Id
    @GeneratedValue
    private Long id;

	private List<String> meals = new ArrayList<>();
	
	@StartNode
	private Plain plain;

	@EndNode
	private Course course;

	public Meal() {
	}

	public Meal(Plain plain, Course course) {
		this.plain = plain;
		this.course = course;
	}

	public Plain getPlain() {
		return plain;
	}

	public void setPlain(Plain plain) {
		this.plain = plain;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<String> getMeals() {
	    return meals;
	}
	
	 public void addMeal(String name) {
	    if (this.meals == null) {
	        this.meals = new ArrayList<>();
	    }
	    
	    this.meals.add(name);
	 }
}

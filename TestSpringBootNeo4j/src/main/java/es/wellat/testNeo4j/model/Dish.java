package es.wellat.testNeo4j.model;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Relacion entre comidas (Course) <--> platos (Dish)
 * @author jeag2
 */

@RelationshipEntity(type = "DISH")
public class Dish {
	
    @Id
    @GeneratedValue
    private Long id;
    
    private List<String> dishes = new ArrayList<>();

	@StartNode
	private Course course;

	@EndNode
	private Plate plate;
	
	public Dish() {
	}

	public Dish(Course course, Plate plate) {
		this.course = course;
		this.plate = plate;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Plate getPlate() {
		return plate;
	}

	public void setPlate(Plate plate) {
		this.plate = plate;
	}
	
	public List<String> getDishes() {
	    return dishes;
	}
	
	public void addDish(String name) {
	    if (this.dishes == null) {
	        this.dishes = new ArrayList<>();
	    }
	    this.dishes.add(name);
	 }
	
    
    
    
    
}

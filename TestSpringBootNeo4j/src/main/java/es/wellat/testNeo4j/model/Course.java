package es.wellat.testNeo4j.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


/**
 * Tipo de Meal (Desayuno, Comida, Merienda, Cena)
 * @author jeag2
 */

@NodeEntity
public class Course {
	
	 @Id
	 @GeneratedValue
     private Long id;
	 
	 private String type;
      
	

	private String name;
	 

	@Relationship(type = "MEAL")
	 private List<Plain> plains = new ArrayList<>();
	 
	 //@Relationship(type = "DISH", direction = Relationship.INCOMING)
	  
	 @Relationship(type = "DISH")
	 private List<Dish> dishes;
	 
	 public Course() {
	 }
	 
	 public Course(String name, String type) {
		 this.name = name;
		 this.type = type;
	 }
	 
	 public List<Plain> getPlains() {
			return plains;
	 }

	public void addPlains(Plain plain) {
		  if (this.plains == null) {
			  this.plains = new ArrayList<>();
		  }
		 this.plains.add(plain);
	}
	
	 public List<Dish> getDishes() {
			return dishes;
	 }

	public void addDish(Dish dish) {
		  if (this.dishes == null) {
			  this.dishes = new ArrayList<>();
		  }
		 this.dishes.add(dish);
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	  

}

package es.wellat.testNeo4j.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




/**
 * Coleccion de desayunos/comidas/cenas de un dia
 * @author jeag2
 */


@NodeEntity
public class Plain {
	
	 @Id 
	 @GeneratedValue 
	 private Long id;
	 private String name;
	 
	 //@Relationship(type = "MEAL", direction = Relationship.INCOMING)
	 @Relationship(type = "MEAL")
	 private List<Meal> meals;
	 
	 
	 public Plain() {
	 }
	 
	 public Plain(String name) {
		 this.name = name;
	 }
	 
	 public List<Meal> getMeals() {
			return meals;
	 }

	public void addMeals(Meal meal) {
		  if (this.meals == null) {
			  this.meals = new ArrayList<>();
		  }
		 this.meals.add(meal);
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
	
	
	@Override
	public String toString() {
		return "[PLAIN] id " +  this.id + " name " +  this.name;
				
	}
}

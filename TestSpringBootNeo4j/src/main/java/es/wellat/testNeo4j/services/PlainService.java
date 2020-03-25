package es.wellat.testNeo4j.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.wellat.testNeo4j.model.Course;
import es.wellat.testNeo4j.model.Dish;
import es.wellat.testNeo4j.model.Meal;
import es.wellat.testNeo4j.model.Plain;
import es.wellat.testNeo4j.model.response.ResponseBean;
import es.wellat.testNeo4j.repositories.PlainRepository;


@Service
public class PlainService {
	
	
	private final PlainRepository plainRepository;
	
	public PlainService(PlainRepository plainRepository) {
		this.plainRepository = plainRepository;
	}
	
	
	private List<ResponseBean> toResponseBean(Collection<Plain> plain) {
		
		List<ResponseBean> beans = new ArrayList<ResponseBean>();
		
		Iterator<Plain> plainIt = plain.iterator();  
		
		while(plainIt.hasNext()) {
			
			Plain p = plainIt.next();
			
			if (p.getMeals() != null) {
				if (!p.getMeals().isEmpty()) {
					for(Meal meal: p.getMeals()) {
						Course course = meal.getCourse();
						if (course.getDishes() != null) {
							if (!course.getDishes().isEmpty()) {
								for(Dish dish: course.getDishes()) {
									ResponseBean rB = new ResponseBean();
									rB.setName(p.getName());
									rB.setCourse(course.getName());
									rB.setIngredients(dish.getPlate().getName());
									beans.add(rB);	
								}
							}
						}
					}
				}
			}
		}
		
		return beans;
		
		
	}
	
	
	private Map<String, Object> toD3Format(Collection<Plain> plain) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		
		Iterator<Plain> plainIt = plain.iterator();  
		
		int i = 1;
		
		List<List<Meal>> data = new ArrayList<>();
		
		while(plainIt.hasNext()) {
			Plain p = plainIt.next();
			
			if (!nodes.contains(map("id",p.getId(),"name",p.getName()))) {
				nodes.add(map("id",p.getId(),"name",p.getName()));
			}
			
			if (p.getMeals() != null) {
				if (!p.getMeals().isEmpty()) {
					for(Meal meal: p.getMeals()) {
						if (!nodes.contains(map("id",meal.getCourse().getId(),"name",meal.getCourse().getName()))) {
							nodes.add(map("id",meal.getCourse().getId(),"name",meal.getCourse().getName()));
						}
						
						
						if (!rels.contains(map("source",p.getId(),"target",meal.getCourse().getId()))) {
							rels.add(map("source",p.getId(),"target",meal.getCourse().getId()));
						}
						
					
						
						Course course = meal.getCourse();
						
						if (course.getDishes() != null) {
							if (!course.getDishes().isEmpty()) {
								
								for(Dish dish: course.getDishes()) {
									
									if (!nodes.contains(map("id",dish.getPlate().getId(),"name",dish.getPlate().getName()))) {
										nodes.add(map("id",dish.getPlate().getId(),"name",dish.getPlate().getName()));
									}
									
									
									if (!rels.contains(map("source",course.getId(),"target",dish.getPlate().getId()))) {
										rels.add(map("source",course.getId(),"target",dish.getPlate().getId()));
									}
									
									
								}
							}
						}
						
						
					}
				}
			}
			
		}
		
		System.out.println("nodes " + nodes.toString());
		System.out.println("links " + rels.toString());
		
		return map("nodes", nodes, "links", rels);
	}
	
	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}
	
	@Transactional(readOnly = true)
	public List<Plain> findAll() {
		return plainRepository.findAll();
	}
	
	 @Transactional(readOnly = true)
	 public Plain findByTitle(String name) {
	        Plain result = plainRepository.findByName(name);
	        return result;
	 }
	 
	 @Transactional(readOnly = true)
	 private Collection<Plain> graph(int limit, String course, String ingredients){

		   Collection<Plain> result = null;
		 
			if (!course.equalsIgnoreCase("*") && ingredients.equalsIgnoreCase("*")) {
				
				result = plainRepository.graphCourse(course);
				
			}else if (!course.equalsIgnoreCase("*") && !ingredients.equalsIgnoreCase("*")) {
				
				result = plainRepository.graphCourseNoIngredient(course, ingredients);
				
			}else if (course.equalsIgnoreCase("*") && !ingredients.equalsIgnoreCase("*")) {
					
				result = plainRepository.graphNoIngredient(ingredients);
				
	 		}else {
				result = plainRepository.graph(limit);
			}
			
			return result;
	 }
	 
	 
	 @Transactional(readOnly = true)
	  public List<ResponseBean>  query(int limit, String course, String ingredients) {
			Collection<Plain> result = null;
			result = graph(limit, course, ingredients);
			return toResponseBean(result);
	 }

	@Transactional(readOnly = true)
	public Map<String, Object>  graphD3(int limit, String course, String ingredients) {
		Collection<Plain> result = null;
	    
		result = graph(limit, course, ingredients);
				
		return toD3Format(result);
	}

}

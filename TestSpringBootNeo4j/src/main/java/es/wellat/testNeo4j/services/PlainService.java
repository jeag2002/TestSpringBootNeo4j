package es.wellat.testNeo4j.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.wellat.testNeo4j.controller.PlainFoodController;
import es.wellat.testNeo4j.model.Course;
import es.wellat.testNeo4j.model.Dish;
import es.wellat.testNeo4j.model.Meal;
import es.wellat.testNeo4j.model.Plain;
import es.wellat.testNeo4j.model.response.ResponseBean;
import es.wellat.testNeo4j.repositories.PlainRepository;
import es.wellat.testNeo4j.repositories.session.QuerySession;


@Service
public class PlainService {
	
	Logger log = LoggerFactory.getLogger(PlainService.class);

	
	@Autowired
	QuerySession qS;
	
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
		
		log.info("[PLAINSERVICE] nodes " + nodes.toString());
		log.info("[PLAINSERVICE] links " + rels.toString());
		
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
		   
		   List<String> courses = new ArrayList<String>();
		   List<String> ingredientL = new ArrayList<String>();
		   
		 
			if (!course.equalsIgnoreCase("*") && ingredients.equalsIgnoreCase("*")) {
				
				String data[] = course.split(",");
				courses = Arrays.asList(data);
				
				if (courses.size() == 1) {
					result = plainRepository.graphCourse(courses.get(0));
				}else {
					result = qS.courses(courses);
				}
				
				
			}else if (!course.equalsIgnoreCase("*") && !ingredients.equalsIgnoreCase("*")) {
				
				String data[] = course.split(",");
				courses = Arrays.asList(data);
				
				data = ingredients.split(",");
				ingredientL = Arrays.asList(data);
				
				if ((courses.size() == 1) && (ingredientL.size() == 1)) {
					result = plainRepository.graphCourseNoIngredient(courses.get(0), ingredientL.get(0));
				}else {
					log.info("[PLAINSERVICE] not yet implemented");
				}
				
			}else if (course.equalsIgnoreCase("*") && !ingredients.equalsIgnoreCase("*")) {
				
				String data[] = ingredients.split(",");
				ingredientL = Arrays.asList(data);
				
				if (ingredientL.size() == 1) {
					result = plainRepository.graphNoIngredient(ingredients);
				}else {
					log.info("[PLAINSERVICE] not yet implemented");
				}
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

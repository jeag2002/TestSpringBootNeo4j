package es.wellat.testNeo4j.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.wellat.testNeo4j.model.response.ResponseBean;
import es.wellat.testNeo4j.services.PlainService;


@RestController
@RequestMapping("/")
public class PlainFoodController {
	
	private final PlainService plainService;
	
	public PlainFoodController(PlainService plainService) {
		this.plainService = plainService;
	}

    
    @GetMapping("/graph")
	public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit,
			                         @RequestParam(value = "course", required = false) String course, 
			                         @RequestParam(value = "ingredients", required = false)String ingredients) {
    	
    	int limitInfo = limit == null ? 100 : limit;
    	String coursePtr = course == null? "*":course;
    	String ingredientsPtr = ingredients == null? "*":ingredients;
    	
    	
    	System.out.println("GRAPH limitInfo " + limitInfo + " course " +  coursePtr +  " ingredients " +  ingredientsPtr);
    	
		return plainService.graphD3(limitInfo,coursePtr,ingredientsPtr);
	}
    
    @GetMapping("/query")
    public List<ResponseBean> query(@RequestParam(value = "course", required = false) String course, @RequestParam(value = "ingredients", required = false)String ingredients) {
    	
    	int limitInfo = 100;
    	String coursePtr = course == null? "*":course;
    	String ingredientsPtr = ingredients == null? "*":ingredients;
    	
    	System.out.println("QUERY limitInfo " + limitInfo + " course " +  coursePtr +  " ingredients " +  ingredientsPtr);
    	
    	return plainService.query(limitInfo, coursePtr, ingredientsPtr);
    	
    }
    
    
    

}

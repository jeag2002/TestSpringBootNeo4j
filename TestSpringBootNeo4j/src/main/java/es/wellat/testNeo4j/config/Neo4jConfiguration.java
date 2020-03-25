package es.wellat.testNeo4j.config;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.wellat.testNeo4j.model.Course;
import es.wellat.testNeo4j.model.Dish;
import es.wellat.testNeo4j.model.Meal;
import es.wellat.testNeo4j.model.Plain;
import es.wellat.testNeo4j.model.Plate;
import es.wellat.testNeo4j.repositories.PlainRepository;

@Configuration
public class Neo4jConfiguration {

	private final static Logger log = LoggerFactory.getLogger(Neo4jConfiguration.class);
	
	
	@Bean 
	CommandLineRunner demo(PlainRepository plainRepository) {
		
		return args ->{
			
			//wait 10 seconds before neo4j bolt interface finished to launch
			TimeUnit.SECONDS.sleep(10);
			
			plainRepository.deleteAll(); 
			
			
			Plate plate_1 = new Plate("coffee");
			Plate plate_2 = new Plate("toasts");
			Plate plate_3 = new Plate("rice");
			Plate plate_4 = new Plate("meal");
			Plate plate_5 = new Plate("fish");
			Plate plate_6 = new Plate("fruit");
			Plate plate_7 = new Plate("milk");
			
			
			Course course_1 = new Course("breakfast_1","breakfast");
			Course course_2 = new Course("breakfast_2","breakfast");
			
			Course course_3 = new Course("dinner_1","dinner");
			Course course_4 = new Course("dinner_2","dinner");
			
			Course course_5 = new Course("supper_1","supper");
			Course course_6 = new Course("supper_2","supper");
			
			Plain plain_1 = new Plain("Monday");
			
			
			Dish dish_13 = new Dish(course_6, plate_2); //supper_2 --> toast,fruit
			Dish dish_14 = new Dish(course_6, plate_6);
			
			plate_2.addCourse(course_6);
			plate_6.addCourse(course_6);
			
			course_6.addDish(dish_13);
			course_6.addDish(dish_14);
			
			course_6.addPlains(plain_1);
			
			
			Dish dish_11 = new Dish(course_5,plate_6); //supper_1 --> milk, fruit
			Dish dish_12 = new Dish(course_5,plate_7);
			
			plate_6.addCourse(course_5);
			plate_7.addCourse(course_5);
			
			course_5.addDish(dish_11);
			course_5.addDish(dish_12);
			
			course_5.addPlains(plain_1);
			
			
			Dish dish_8 = new Dish(course_4, plate_3); //dinner_2 --> rice, fish, fruit
			Dish dish_9 = new Dish(course_4, plate_5);
			Dish dish_10 = new Dish(course_4, plate_6);
			
			plate_3.addCourse(course_4);
			plate_5.addCourse(course_4);
			plate_6.addCourse(course_4);
			
			course_4.addDish(dish_8);
			course_4.addDish(dish_9);
			course_4.addDish(dish_10);
			
			course_4.addPlains(plain_1);
			
			Dish dish_5 = new Dish(course_3, plate_3); //dinner_1 --> rice, meal, fruit
			Dish dish_6 = new Dish(course_3, plate_4);
			Dish dish_7 = new Dish(course_3, plate_6);
			
			plate_3.addCourse(course_3);
			plate_4.addCourse(course_3);
			plate_6.addCourse(course_3);
			
			course_3.addDish(dish_5);
			course_3.addDish(dish_6);
			course_3.addDish(dish_7);
			
			course_3.addPlains(plain_1);
			
			Dish dish_3 = new Dish(course_2,plate_2); //breakfast_2 --> milk,toasts
			Dish dish_4 = new Dish(course_2,plate_7);
			
			plate_2.addCourse(course_2);
			plate_7.addCourse(course_2);
			
			course_2.addDish(dish_3);
			course_2.addDish(dish_4);
			
			course_2.addPlains(plain_1);
			
			Dish dish_1 = new Dish(course_1,plate_1); //breakfast_1 --> coffee,toasts
			Dish dish_2 = new Dish(course_1,plate_2);
			
			plate_1.addCourse(course_1);
		    plate_2.addCourse(course_1);
		    
		    course_1.addDish(dish_1);
		    course_1.addDish(dish_2);
		    
		    course_1.addPlains(plain_1);
		    
		    Meal meal_1 = new Meal(plain_1, course_1);
		    Meal meal_2 = new Meal(plain_1, course_2);
		    Meal meal_3 = new Meal(plain_1, course_3);
		    Meal meal_4 = new Meal(plain_1, course_4);
		    Meal meal_5 = new Meal(plain_1, course_5);
		    Meal meal_6 = new Meal(plain_1, course_6);
		    
		    plain_1.addMeals(meal_1);
		    plain_1.addMeals(meal_2);
		    plain_1.addMeals(meal_3);
		    plain_1.addMeals(meal_4);
		    plain_1.addMeals(meal_5);
		    plain_1.addMeals(meal_6);
		    
		    plainRepository.save(plain_1);
		    
		    Plain plain_2 = new Plain("Tuesday");
		    
		    Meal meal_8 = new Meal(plain_2, course_1); 
		    Meal meal_9 = new Meal(plain_2, course_2);
		    
		    plain_2.addMeals(meal_8);
		    plain_2.addMeals(meal_9);
		    
		    plainRepository.save(plain_2);
		    
		};
		
	}
	
	
	
	
	/*
	@Bean
	CommandLineRunner demo(PersonRepository personRepository) {
		return args -> {

			personRepository.deleteAll();

			Person greg = new Person("Greg");
			Person roy = new Person("Roy");
			Person craig = new Person("Craig");

			List<Person> team = Arrays.asList(greg, roy, craig);

			log.info("Before linking up with Neo4j...");

			team.stream().forEach(person -> log.info("\t" + person.toString()));

			personRepository.save(greg);
			personRepository.save(roy);
			personRepository.save(craig);

			greg = personRepository.findByName(greg.getName());
			greg.worksWith(roy);
			greg.worksWith(craig);
			personRepository.save(greg);

			roy = personRepository.findByName(roy.getName());
			roy.worksWith(craig);
			// We already know that roy works with greg
			personRepository.save(roy);

			// We already know craig works with roy and greg

			log.info("Lookup each person by name...");
			team.stream().forEach(person -> log.info(
					"\t" + personRepository.findByName(person.getName()).toString()));
		};
	}
	*/
	
	
}

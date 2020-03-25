package es.wellat.testNeo4j.model.response;

public class ResponseBean {


	private String name;
	private String course;
	private String ingredients;
	
	public ResponseBean() {
		
		this.name = "";
		this.course = "";
		this.ingredients = "";
		
	}
	
	public ResponseBean(String name, String course, String ingredient) {
		super();
		this.name = name;
		this.course = course;
		this.ingredients = ingredient;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}


}

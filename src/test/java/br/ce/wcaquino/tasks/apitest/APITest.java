package br.ce.wcaquino.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}
	
	@Test
	public void shouldReturnTasks() {
		RestAssured.given()
		.when()
			.get("/todo")
		.then()
			.statusCode(200);
	}
	
	@Test
	public void shouldAddTask() {
		RestAssured.given()
			.body("{\"task\": \"API Post Test\", \"dueDate\": \"2024-12-30\"})").contentType(ContentType.JSON)
		.when()		
			.post("/todo")
		.then()
			.statusCode(201);
	}
	
	@Test
	public void notAddInvalidTask() {
		RestAssured.given()
			.body("{\"task\": \"API Post Test\", \"dueDate\": \"2020-12-30\"})")
			.contentType(ContentType.JSON)
		.when()		
			.post("/todo")
		.then()
			.statusCode(400)
			.body("message", CoreMatchers.is("Due date must not be in past"));
	}
	
	

}

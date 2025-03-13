package com.techie.microservices.B8.product;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class ProductServiceApplicationTests {

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
		log.info("Running tests on port: {}", port);
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
					"name": "iPhone 15",
					"description": "iPhone 15 is a smartphone from apple",
					"price": 1000
				}
			""";

		RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("iPhone 15"))
				.body("description", Matchers.equalTo("iPhone 15 is a smartphone from apple"))
				.body("price", Matchers.equalTo(1000));
	}
}

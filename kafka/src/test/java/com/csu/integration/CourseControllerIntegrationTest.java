package com.csu.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CourseControllerIntegrationTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Test
  public void givenNoMatchingCourseCodeWhenGetCourseThenRespondWithStatusNotFound() {
    String nonMatchingCourseCode = "nonMatchingCourseCode";

    given()
        .webAppContextSetup(webApplicationContext)
        .when()
        .get("/courses/" + nonMatchingCourseCode)
        .then()
        .log().ifValidationFails()
        .statusCode(NOT_FOUND.value());
  }
}
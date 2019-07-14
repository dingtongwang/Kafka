package com.csu.controller;

import com.csu.advice.CourseControllerExceptionHandler;
import com.csu.exception.CourseNotFoundException;
import com.csu.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {

  @Mock
  private CourseService courseService;

  @InjectMocks
  private CourseController courseController;

  @InjectMocks
  private CourseControllerExceptionHandler courseControllerExceptionHandler;

  @Test
  public void givenNoExistingCoursesWhenGetCoursesThenRespondWithStatusOkAndEmptyArray() {
    when(courseService.getCourses()).thenReturn(Collections.emptyList());

    given()
        .standaloneSetup(courseController)
        .when()
        .get("/courses")
        .then()
        .log().ifValidationFails()
        .statusCode(OK.value())
        .contentType(JSON)
        .body(is(equalTo("[]")));
  }

  @Test
  public void givenNoMatchingCoursesWhenGetCoursesThenRespondWithStatusNotFound() {
    String nonMatchingCourseCode = "nonMatchingCourseCode";

    when(courseService.getCourse(nonMatchingCourseCode)).thenThrow(new CourseNotFoundException(nonMatchingCourseCode));

    given()
        .standaloneSetup(courseController, courseControllerExceptionHandler)
        .when()
        .get("/courses/" + nonMatchingCourseCode)
        .then()
        .log().ifValidationFails()
        .statusCode(NOT_FOUND.value());
  }
}
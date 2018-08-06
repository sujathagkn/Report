package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.config.MatcherConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.config.name=application,InputConfiguration")
public class ReportControllerIt {

    @LocalServerPort
    private int port;


    @Before
    public void setup() {
        RestAssured.port = port;
        RestAssured.config = RestAssured.config().matcherConfig(new MatcherConfig(MatcherConfig.ErrorDescriptionType.HAMCREST));
    }

    @Test
    public void verify_full_structure_with_200() throws org.json.JSONException {
        given().port(port).accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/processReport?inputFile=./src/integration-test/resources/input.txt&outputDir=./src/integration-test/resources")
                .then().statusCode(HTTP_OK);
    }

    @Test
    public void returns_404_for_bad_path() {
        given().accept("application/json")
                .when().get("/bad-path")
                .then().statusCode(HTTP_NOT_FOUND);
    }

    @Test
    public void returns_404() {
        given().accept("application/json")
                .when().get("/processReport?inputFile=./src/integration-test/resources1/input.txt&./src/integration-test/resources1")
                .then().statusCode(HTTP_BAD_REQUEST);
    }
}

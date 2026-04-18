package com.frnz7.restSpring.integrationtests.controller.withyaml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;

import com.frnz7.restSpring.config.TestConfigs;
import com.frnz7.restSpring.integrationtests.controller.withyaml.mapper.YAMLMapper;
import com.frnz7.restSpring.integrationtests.dto.PersonDTO;
import com.frnz7.restSpring.integrationtests.testcontainers.AbstractIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerYAMLTest extends AbstractIntegrationTest {

    private static RequestSpecification requestSpecification;
    private static YAMLMapper objectMapper;
    private static PersonDTO person;

    @BeforeAll
    static void setUp() {
        objectMapper = new YAMLMapper();
        person = new PersonDTO();
    }
    @Order(1)
    @Test
    void createTest() throws IOException {
        mockPerson();
        requestSpecification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ERUDIO)
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var createdPerson = given((RequestSpecification) RestAssuredConfig.config()
                .encoderConfig(
                        EncoderConfig.encoderConfig().
                                encodeContentTypeAs(MediaType.APPLICATION_YAML_VALUE, ContentType.TEXT)))
        .spec(requestSpecification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .port(TestConfigs.SERVER_PORT)
                .body(person, objectMapper)
                .when()
                .post()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                .body()
                .as(PersonDTO.class, objectMapper);

        person = createdPerson;

        assertNotNull(createdPerson.getId());
        assertTrue(createdPerson.getId() > 0);

        assertEquals(1, createdPerson.getId());
        assertEquals("Linus", createdPerson.getFirstName());
        assertEquals("Torvalds", createdPerson.getLastName());
        assertEquals("Helsinki", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
        assertTrue(createdPerson.getEnabled());

    }
    @Order(2)
    @Test
    void updateTest() throws IOException {
        person.setLastName("Benedcit Torvalds");

        var createdPerson = given((RequestSpecification) RestAssuredConfig.config()
                .encoderConfig(
                        EncoderConfig.encoderConfig().
                                encodeContentTypeAs(MediaType.APPLICATION_YAML_VALUE, ContentType.TEXT)))
                .spec(requestSpecification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .port(TestConfigs.SERVER_PORT)
                .body(person, objectMapper)
                .when()
                .patch()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                .body()
                  .as(PersonDTO.class, objectMapper);

        person = createdPerson;

        assertNotNull(createdPerson.getId());
        assertTrue(createdPerson.getId() > 0);

        assertEquals(1, createdPerson.getId());
        assertEquals("Linus", createdPerson.getFirstName());
        assertEquals("Torvalds", createdPerson.getLastName());
        assertEquals("Helsinki", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
        assertTrue(createdPerson.getEnabled());

    }

    @Test
    @Order(3)
    void findByIdTest() throws IOException {
        var createdPerson = given((RequestSpecification) RestAssuredConfig.config()
                .encoderConfig(
                        EncoderConfig.encoderConfig().
                                encodeContentTypeAs(MediaType.APPLICATION_YAML_VALUE, ContentType.TEXT)))
                .spec(requestSpecification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .port(TestConfigs.SERVER_PORT)
                .pathParam("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)

                .extract()
                .body()
                  .as(PersonDTO.class, objectMapper);

        ;
        person = createdPerson;

        assertNotNull(createdPerson.getId());
        assertTrue(createdPerson.getId() > 0);

        assertEquals(1, createdPerson.getId());
        assertEquals("Linus", createdPerson.getFirstName());
        assertEquals("Benedcit Torvalds", createdPerson.getLastName());
        assertEquals("Helsinki", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
        assertTrue(createdPerson.getEnabled());
    }


    @Test
    @Order(4)
    void disableTest() throws IOException {
        var createdPerson = given((RequestSpecification) RestAssuredConfig.config()
                .encoderConfig(
                        EncoderConfig.encoderConfig().
                                encodeContentTypeAs(MediaType.APPLICATION_YAML_VALUE, ContentType.TEXT)))
                .spec(requestSpecification)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .port(TestConfigs.SERVER_PORT)
                .pathParam("id", person.getId())
                .when()
                .patch("{id}")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                .body()
                  .as(PersonDTO.class, objectMapper);

        person = createdPerson;

        assertNotNull(createdPerson.getId());
        assertTrue(createdPerson.getId() > 0);

        assertEquals(1, createdPerson.getId());
        assertEquals("Linus", createdPerson.getFirstName());
        assertEquals("Benedcit Torvalds", createdPerson.getLastName());
        assertEquals("Helsinki", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
        assertFalse(createdPerson.getEnabled());
    }


    @Test
    @Order(5)
    void deleteTest() throws IOException {
        given(requestSpecification)
                .pathParam("id", person.getId())
                .when()
                .delete("{id}")
                .then()
                .statusCode(204);
    }


    @Test
    @Order(6)
    void findAllTest() throws IOException {
        var response = given(requestSpecification)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .port(TestConfigs.SERVER_PORT)
                .pathParam("id", person.getId())
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                .body()
                  .as(PersonDTO[].class, objectMapper);

        List<PersonDTO> people = Arrays.asList(response);


        PersonDTO personEight = people.get(7);
        person = personEight;

        assertNotNull(personEight.getId());
        assertTrue(personEight.getId() > 0);

        assertEquals(1, personEight.getId());
        assertEquals("Ada", personEight.getFirstName());
        assertEquals("Lovelace", personEight.getLastName());
        assertEquals("London - England", personEight.getAddress());
        assertEquals("Male", personEight.getGender());
        assertTrue(personEight.getEnabled());

        PersonDTO personNine = people.get(7);
        person = personNine;

        assertNotNull(personNine.getId());
        assertTrue(personNine.getId() > 0);

        assertEquals(1, personNine.getId());
        assertEquals("Felipe", personNine.getFirstName());
        assertEquals("A", personNine.getLastName());
        assertEquals("SP", personNine.getAddress());
        assertEquals("Male", personNine.getGender());
        assertTrue(personNine.getEnabled());
    }



    private void mockPerson() {
        person.setFirstName("Linus");
        person.setLastName("Torvalds");
        person.setAddress("Helsinki");
        person.setGender("Male");
        person.setEnabled(true);
    }

}
package com.frnz7.restSpring.integrationtests.controller.withxml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.frnz7.restSpring.config.TestConfigs;
import com.frnz7.restSpring.integrationtests.dto.PersonDTO;
import com.frnz7.restSpring.integrationtests.testcontainers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerXMLTest extends AbstractIntegrationTest {

    private static RequestSpecification requestSpecification;
    private static XmlMapper objectMapper;
    private static PersonDTO person;

    @BeforeAll
    static void setUp() {
        objectMapper = new XmlMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

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

        var content = given(requestSpecification)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .port(TestConfigs.SERVER_PORT)
                .body(person)
                .when()
                .post()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .extract()
                .body()
                .asString();

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);
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

        var content = given(requestSpecification)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .port(TestConfigs.SERVER_PORT)
                .body(person)
                .when()
                .patch()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .extract()
                .body()
                .asString();

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);
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
        var content = given(requestSpecification)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .port(TestConfigs.SERVER_PORT)
                .pathParam("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_XML_VALUE)

                .extract()
                .body()
                .asString();

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);
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
        var content = given(requestSpecification)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .port(TestConfigs.SERVER_PORT)
                .pathParam("id", person.getId())
                .when()
                .patch("{id}")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .extract()
                .body()
                .asString();

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);
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
        var content = given(requestSpecification)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .port(TestConfigs.SERVER_PORT)
                .pathParam("id", person.getId())
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .extract()
                .body()
                .asString();

        List<PersonDTO> people = objectMapper.readValue(content, new TypeReference<List<PersonDTO>>(){});


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
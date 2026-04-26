package com.frnz7.restSpring.repository;

import com.frnz7.restSpring.model.Person;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Assertions.*;

import java.awt.print.Pageable;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonRepositoryTest {

    private final PersonRepository repository;
    private static Person person;

    public PersonRepositoryTest(PersonRepository repository){
        this.repository = repository;
    }

    @BeforeAll
    static void setUp() {
        person = new Person();
    }

    @Order(1)
    @Test
    void findPeopleByName() {
        Pageable pageable = (Pageable) PageRequest.of(0,12, Sort.by(Sort.Direction.ASC, "firstName"));
        person = repository.findPeopleByName("iko", (org.springframework.data.domain.Pageable) pageable).getContent().get(0);

      assertNotNull(person);
      assertNotNull(person.getId());
      assertEquals("Nikola", person.getFirstName());
      assertEquals("Tesla", person.getLastName());
      assertEquals("Smiljan - Croatia", person.getAddress());
      assertEquals("Male", person.getGender());
      assertTrue(person.getEnabled());
    }
    @Order(2)
    @Test
    void disablePerson() {
        Long id = person.getId();
        repository.disablePerson(id);

        var result = repository.findById(id);
        person = result.get();

        assertNotNull(person);
        assertNotNull(person.getId());
        assertEquals("Nikola", person.getFirstName());
        assertEquals("Tesla", person.getLastName());
        assertEquals("Smiljan - Croatia", person.getAddress());
        assertEquals("Male", person.getGender());
        assertFalse(person.getEnabled());
    }

}
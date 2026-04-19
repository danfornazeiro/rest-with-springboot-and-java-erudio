package com.frnz7.restSpring.unitetests.service;

import com.frnz7.restSpring.data.dto.PersonDTO;
import com.frnz7.restSpring.exception.RequiredObjectIsNullException;
import com.frnz7.restSpring.model.Person;
import com.frnz7.restSpring.repository.PersonRepository;
import com.frnz7.restSpring.service.PersonServices;
import com.frnz7.restSpring.unitetests.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        //MockitoAnnotations.openMocks(this);
    }

    @Test
    @Disabled("REASON: still under development")
    void findAll() {

        List<Person> list = input.mockEntityList();
        when(personRepository.findAll()).thenReturn(list);
        List<PersonDTO> people = new ArrayList<>(); //service.findAll(pageable);

        assertNotNull(people);
        assertEquals(14, list.size());

        var person1 = people.get(1);

        assertNotNull(person1);
        assertNotNull(person1.getId());
        assertNotNull(person1.getLinks());
        assertNotNull(person1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(person1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(person1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("POST")
                )
        );
        assertNotNull(person1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("PUT")
                )
        );
        assertNotNull(person1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("DELETE")
                )
        );

        assertEquals("First Name Test1", person1.getFirstName());
        assertEquals("Last Name Test1", person1.getLastName());
        assertEquals("Address Test1", person1.getAddress());
        assertEquals("Female", person1.getGender());

        var person4 = people.get(4);

        assertNotNull(person4);
        assertNotNull(person4.getId());
        assertNotNull(person4.getLinks());
        assertNotNull(person4.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(person4.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(person4.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("POST")
                )
        );
        assertNotNull(person4.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("PUT")
                )
        );
        assertNotNull(person4.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("DELETE")
                )
        );

        assertEquals("First Name Test4", person4.getFirstName());
        assertEquals("Last Name Test4", person4.getLastName());
        assertEquals("Address Test4", person4.getAddress());
        assertEquals("Male", person4.getGender());

        var person7 = people.get(7);

        assertNotNull(person7);
        assertNotNull(person7.getId());
        assertNotNull(person7.getLinks());
        assertNotNull(person7.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(person7.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(person7.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("POST")
                )
        );
        assertNotNull(person7.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("PUT")
                )
        );
        assertNotNull(person7.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("DELETE")
                )
        );

        assertEquals("First Name Test7", person7.getFirstName());
        assertEquals("Last Name Test7", person7.getLastName());
        assertEquals("Address Test7", person7.getAddress());
        assertEquals("Female", person7.getGender());
    }

    @Test
    void findById() {

        Person person = input.mockEntity(1);
        person.setId(1L);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("POST")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("PUT")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("DELETE")
                )
        );

        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());

    }

    @Test
    void create() {

        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(personRepository.save(person)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("POST")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("PUT")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("DELETE")
                )
        );

        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());

    }

    @Test
    void testCreateWithNullPerson(){
        Exception e = assertThrows(RequiredObjectIsNullException.class,
                () -> {
            service.create(null);
                });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void update() {

        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("POST")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("PUT")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("DELETE")
                )
        );

        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());

    }

    @Test
    void testUpdateWithNullPerson(){
        Exception e = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    service.create(null);
                });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void delete() {

        Person person = input.mockEntity(1);
        person.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        service.delete(1L);
        verify(personRepository,times(1)).findById(anyLong());
        verify(personRepository,times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(personRepository);
    }
}
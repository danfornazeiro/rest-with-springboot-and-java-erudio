package com.frnz7.restSpring.controller;

import com.frnz7.restSpring.controller.docs.PersonControllerDocs;
import com.frnz7.restSpring.data.dto.PersonDTO;
import com.frnz7.restSpring.service.PersonServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for <anaging people")
public class PersonController implements PersonControllerDocs {

    private final PersonServices personServices;
    public  PersonController(PersonServices personServices) {
        this.personServices = personServices;
    }

    @GetMapping(produces =
            {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}
    )
    @Override
    public List<PersonDTO> findAll() {
        return personServices.findAll();
    }

    @GetMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE
    })
    @Override
    public PersonDTO findById(@PathVariable Long id) {
        return personServices.findById(id);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}
    )

    @Override
    public PersonDTO create(@RequestBody PersonDTO person){
        return personServices.create(person);
    }


    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}
    )

    @Override
    public PersonDTO update(@RequestBody PersonDTO person) {
        return personServices.update(person);
    }

    @PatchMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE
    })
    @Override
    public PersonDTO disablePerson(@PathVariable Long id){
        return personServices.disablePerson(id);
    }


    @DeleteMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE
    })

    @Override
    public ResponseEntity<?> delete(@PathVariable Long id) {
         personServices.delete(id);
         return ResponseEntity.noContent().build();
    }
}

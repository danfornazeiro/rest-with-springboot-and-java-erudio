package com.frnz7.restSpring.controller;

import com.frnz7.restSpring.data.dto.PersonDTO;
import com.frnz7.restSpring.service.PersonServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    private final PersonServices personServices;
    public  PersonController(PersonServices personServices) {
        this.personServices = personServices;
    }

    @GetMapping(produces =
            {MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE}
    )
    public List<PersonDTO> findAll() {
        return personServices.findAll();
    }

    @GetMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE
    })
    public PersonDTO findById(@PathVariable Long id) {
        return personServices.findById(id);
    }

    @PostMapping(value = "/v2")
    public PersonDTO create(@RequestBody PersonDTO person){
        return personServices.create(person);
    }


    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE}
    )
    public PersonDTO update(@RequestBody PersonDTO person) {
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<?> deleteAll(@PathVariable Long id) {
         personServices.delete(id);
         return ResponseEntity.noContent().build();
    }
}

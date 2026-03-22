package com.frnz7.restSpring.service;

import com.frnz7.restSpring.controller.PersonController;
import com.frnz7.restSpring.data.dto.PersonDTO;
import com.frnz7.restSpring.exception.ResoureceNotFoundException;
import static com.frnz7.restSpring.mapper.ObjectMapper.parseListObjects;
import static com.frnz7.restSpring.mapper.ObjectMapper.parseObject;

import com.frnz7.restSpring.exception.RequiredObjectIsNullException;
import com.frnz7.restSpring.model.Person;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.frnz7.restSpring.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonServices {

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    private final PersonRepository personRepository;
    public PersonServices(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findAll(){
        logger.info("Finding all people!");
       var people = parseListObjects(personRepository.findAll(), PersonDTO.class);
       people.forEach(this::addHateoasLinks);
       return people;
    }


    public PersonDTO findById(Long id){
        logger.info("Finding one person!");
       var entity = personRepository.findById(id).orElseThrow(
               () -> new ResoureceNotFoundException("No records found for this id")
       );
        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;

    }



    public PersonDTO create(PersonDTO person){

        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("creating one person!");

        var entity = parseObject(person, Person.class);

        entity = personRepository.save(entity);

       var dto = parseObject(entity,PersonDTO.class) ;
        addHateoasLinks(dto);
        return dto;

    }

    public PersonDTO update(PersonDTO person){

        if(person == null) throw new RequiredObjectIsNullException();


        logger.info("updating one person!");
        Person entity = personRepository.findById(person.getId()).orElseThrow(
                () -> new ResoureceNotFoundException("No records found for this id")
        );

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(personRepository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id){
        logger.info("deleting one person!");

        Person entity = personRepository.findById(id).orElseThrow(
                () -> new ResoureceNotFoundException("No records found for this id")
        );
        personRepository.delete(entity);
    }

    private void addHateoasLinks( PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
    }

}

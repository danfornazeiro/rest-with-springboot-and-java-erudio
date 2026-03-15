package com.frnz7.restSpring.service;

import com.frnz7.restSpring.exception.ResoureceNotFoundException;
import com.frnz7.restSpring.model.Person;
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

    public List<Person> findAll(){
        logger.info("Finding all people!");
      return personRepository.findAll();
    }


    public Person findById(Long id){
        logger.info("Finding one person!");
       return personRepository.findById(id).orElseThrow(
               () -> new ResoureceNotFoundException("No records found for this id")
       );

    }

    public Person create(Person person){
        logger.info("creating one person!");
       return personRepository.save(person);
    }

    public Person update(Person person){
        logger.info("updating one person!");
        Person entity = personRepository.findById(person.getId()).orElseThrow(
                () -> new ResoureceNotFoundException("No records found for this id")
        );

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(entity);
    }

    public void delete(Long id){
        logger.info("deleting one person!");

        Person entity = personRepository.findById(id).orElseThrow(
                () -> new ResoureceNotFoundException("No records found for this id")
        );
        personRepository.delete(entity);
    }

}

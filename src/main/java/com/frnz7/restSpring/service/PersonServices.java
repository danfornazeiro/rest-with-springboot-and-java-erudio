package com.frnz7.restSpring.service;

import com.frnz7.restSpring.data.dto.PersonDTO;
import com.frnz7.restSpring.exception.ResoureceNotFoundException;
import static com.frnz7.restSpring.mapper.ObjectMapper.parseListObjects;
import static com.frnz7.restSpring.mapper.ObjectMapper.parseObject;
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

    public List<PersonDTO> findAll(){
        logger.info("Finding all people!");
        return parseListObjects(personRepository.findAll(), PersonDTO.class);
    }


    public PersonDTO findById(Long id){
        logger.info("Finding one person!");
       var entity = personRepository.findById(id).orElseThrow(
               () -> new ResoureceNotFoundException("No records found for this id")
       );
       return parseObject(entity, PersonDTO.class);

    }

    public PersonDTO create(PersonDTO person){
        logger.info("creating one person!");

        var entity = parseObject(person, Person.class);

        entity = personRepository.save(entity);

       return parseObject(entity,PersonDTO.class) ;
    }

    public PersonDTO update(PersonDTO person){
        logger.info("updating one person!");
        Person entity = personRepository.findById(person.getId()).orElseThrow(
                () -> new ResoureceNotFoundException("No records found for this id")
        );

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("deleting one person!");

        Person entity = personRepository.findById(id).orElseThrow(
                () -> new ResoureceNotFoundException("No records found for this id")
        );
        personRepository.delete(entity);
    }

}

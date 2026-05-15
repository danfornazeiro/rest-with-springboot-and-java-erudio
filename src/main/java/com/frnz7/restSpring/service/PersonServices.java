package com.frnz7.restSpring.service;

import com.frnz7.restSpring.controller.PersonController;
import com.frnz7.restSpring.data.dto.PersonDTO;
import com.frnz7.restSpring.exception.FileStorageException;

import static com.frnz7.restSpring.mapper.ObjectMapper.parseObject;

import com.frnz7.restSpring.exception.BadRequestException;
import com.frnz7.restSpring.file.exporter.contract.PersonExporter;
import com.frnz7.restSpring.file.exporter.factory.FileExporterFactory;
import com.frnz7.restSpring.file.importer.contract.FileImporter;
import com.frnz7.restSpring.file.importer.factory.FileImporterFactory;
import com.frnz7.restSpring.model.Person;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.frnz7.restSpring.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServices {

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private FileImporterFactory importer;
    @Autowired
    private FileExporterFactory exporter;
    @Autowired
    private PagedResourcesAssembler<PersonDTO> assembler;

    public PagedModel<EntityModel<PersonDTO>> findAll(Pageable pageable) {
        logger.info("Finding all people!");

        var people = personRepository.findAll(pageable);
        return BuildPagedModel(pageable, people);
    }


    public PagedModel<EntityModel<PersonDTO>> findByName(String firstName, Pageable pageable) {
        logger.info("Finding people by name!");
        var people = personRepository.findPeopleByName(firstName, pageable);
        return BuildPagedModel(pageable, people);
    }


    public Resource exportPerson(Long id, String acceptHeader) {
        logger.info("Exporting data of one person!");
        var person = personRepository.findById(id)
                .map(entity ->  parseObject(entity, PersonDTO.class))
                .orElseThrow(() -> new FileStorageException("No records found for this id"));
        try{
            PersonExporter exporter = this.exporter.getExporter(acceptHeader);
            return exporter.exportPerson(person);
        } catch (Exception e) {
            throw new RuntimeException("Error during file export!",e);
        }

    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person!");
        var entity = personRepository.findById(id).orElseThrow(
                () -> new FileStorageException("No records found for this id")
        );
        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }


    public Resource exportPage(Pageable pageable, String acceptHeader) {
        logger.info("Exporting a people page!");

        var people = personRepository.findAll(pageable)
                .map(person -> parseObject(person, PersonDTO.class))
                .getContent();
        try {
            PersonExporter exporter = this.exporter.getExporter(acceptHeader);
            return exporter.exportPeople(people);
        } catch (Exception e) {
            throw new RuntimeException("Error during file export!", e);
        }
    }

    public PersonDTO create(PersonDTO person) {
        if (person == null) throw new BadRequestException();
        logger.info("creating one person!");

        var entity = parseObject(person, Person.class);
        entity = personRepository.save(entity);

        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;

    }

    public List<PersonDTO> massCreation(MultipartFile file) throws Exception {
        logger.info("Importing people from file!");

        if (file.isEmpty()) {
            throw new BadRequestException("please set a valid file!");
        }
        try (InputStream inputStream = file.getInputStream()) {
            String fileName = Optional.ofNullable(file.getOriginalFilename()).orElseThrow(() ->
                    new BadRequestException("File name cannot be null"));
            FileImporter importer = this.importer.getImporter(fileName);

            List<Person> entities = importer.importFile(inputStream).stream()
                    .map(dto -> personRepository.save(parseObject(dto, Person.class)))
                    .toList();

            return entities.stream().map(
                    e -> {
                        var dto = parseObject(e, PersonDTO.class);
                        addHateoasLinks(dto);
                        return dto;
                    }
            ).toList();
        } catch (Exception e) {
            throw new FileStorageException("Error reading the file: " + e.getMessage());
        }
    }

    public PersonDTO update(PersonDTO person) {

        if (person == null) throw new BadRequestException();


        logger.info("updating one person!");
        Person entity = personRepository.findById(person.getId()).orElseThrow(
                () -> new FileStorageException("No records found for this id")
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

    @Transactional
    public PersonDTO disablePerson(Long id) {
        logger.info("disabling one person!");


        personRepository.findById(id).orElseThrow(
                () -> new FileStorageException("No records found for this id"));

        personRepository.disablePerson(id);

        var entity = personRepository.findById(id).get();

        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("deleting one person!");

        Person entity = personRepository.findById(id).orElseThrow(
                () -> new FileStorageException("No records found for this id")
        );
        personRepository.delete(entity);

    }

    private PagedModel<EntityModel<PersonDTO>> BuildPagedModel(Pageable pageable, Page<Person> people) {
        var peopleWithLinks = people.map(p -> {
            var dto = parseObject(p, PersonDTO.class);
            addHateoasLinks(dto);
            return dto;
        });
        Link findAllLink = WebMvcLinkBuilder.linkTo
                        (WebMvcLinkBuilder.methodOn(PersonController.class)
                                .findAll(pageable.getPageNumber(), pageable.getPageSize(),
                                        String.valueOf(pageable.getSort())))
                .withSelfRel();

        return assembler.toModel(peopleWithLinks, findAllLink);
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll(1, 12, "asc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findByName("", 1, 12, "asc")).withRel("findByName")
                .withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class)).slash("massCreation").withRel("masCreation").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId())).withRel("disable").withType("PATCH"));
        dto.add(linkTo(methodOn(PersonController.class)
                        .exportPage(1, 12, "asc", null))
                        .withRel("exportPage")
                        .withType("GET")
        );
    }

}

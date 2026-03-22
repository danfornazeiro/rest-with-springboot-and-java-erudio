package com.frnz7.restSpring.service;

import com.frnz7.restSpring.controller.BookController;
import com.frnz7.restSpring.controller.PersonController;
import com.frnz7.restSpring.data.dto.BookDTO;
import static com.frnz7.restSpring.mapper.ObjectMapper.parseListObjects;
import static com.frnz7.restSpring.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.frnz7.restSpring.exception.RequiredObjectIsNullException;
import com.frnz7.restSpring.exception.ResoureceNotFoundException;
import com.frnz7.restSpring.model.Books;
import com.frnz7.restSpring.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> findAll(){
     var books = parseListObjects(bookRepository.findAll(), BookDTO.class);
     books.forEach(this::addHateoasLinks);
     return books;
    }

    public BookDTO findById(Long id){
        var entity = bookRepository.findById(id).orElseThrow(
                () -> new ResoureceNotFoundException("No records found for this id")
        );

        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO create(BookDTO bookDTO){
        if(bookDTO == null) throw new RequiredObjectIsNullException();

        var entity = parseObject(bookDTO, Books.class);

        entity = bookRepository.save(entity);
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO bookDTO){
        if(bookDTO == null) throw new RequiredObjectIsNullException();

        Books entity = bookRepository.findById(bookDTO.getId()).orElseThrow(
                () -> new ResoureceNotFoundException("No records found for this id")
        );

        entity.setAuthor(bookDTO.getAuthor());
        entity.setLaunch_date(bookDTO.getLaunch_date());
        entity.setPrice(bookDTO.getPrice());
        entity.setTitle(bookDTO.getTitle());

        var dto = parseObject(bookRepository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;

    }


     public void delete(Long id){
         Books entity =  bookRepository.findById(id).orElseThrow(
                () -> new ResoureceNotFoundException("No records found for this id")
        );
        bookRepository.delete(entity);
     }






    private void addHateoasLinks( BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
    }

}

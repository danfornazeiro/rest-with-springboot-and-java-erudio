package com.frnz7.restSpring.controller;

import com.frnz7.restSpring.controller.docs.BookControllerDocs;
import com.frnz7.restSpring.data.dto.BookDTO;
import com.frnz7.restSpring.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for books")
public class BookController implements BookControllerDocs {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"}
    )

    @Override
    public List<BookDTO> findAll(){
        return bookService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})

    @Override
    public BookDTO findById(@PathVariable Long id){
        return bookService.findById(id);
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    @Override
    public BookDTO create(@RequestBody BookDTO bookDTO){
        return bookService.create(bookDTO);
    }

    @PutMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})

    @Override
    public BookDTO update(@RequestBody BookDTO bookDTO){
        return bookService.update(bookDTO);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    @Override
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }

}

package com.frnz7.restSpring.unitetests.mocks;

import com.frnz7.restSpring.data.dto.BookDTO;
import com.frnz7.restSpring.model.Books;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MockBook {

    public Books mockEntity() {
        return mockEntity(0);
    }

    public BookDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Books> mockEntityList() {
        List<Books> book = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            book.add(mockEntity(i));
        }
        return book;
    }

    public List<BookDTO> mockDTOList() {
        List<BookDTO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockDTO(i));
        }
        return books;
    }

    public Books mockEntity(Integer number) {
        Books books = new Books();
        books.setAuthor("author Test" + number);
        books.setPrice(10.00 + number);
        books.setTitle("title Test" + number);
        books.setId(Long.valueOf(number));
        books.setLaunch_date(Timestamp.valueOf("2026-03-21 23:11:55.443"));
        return books;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO books = new BookDTO();
        books.setAuthor("author Test" + number);
        books.setPrice(10.00 + number);
        books.setTitle("title Test" + number);
        books.setId(number.longValue());
        books.setLaunch_date(Timestamp.valueOf("2026-03-21 23:11:55.443"));
        return books;
    }

}

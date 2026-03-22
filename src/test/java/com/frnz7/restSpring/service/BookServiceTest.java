package com.frnz7.restSpring.service;

import com.frnz7.restSpring.data.dto.BookDTO;
import com.frnz7.restSpring.data.dto.PersonDTO;
import com.frnz7.restSpring.model.Books;
import com.frnz7.restSpring.repository.BookRepository;
import com.frnz7.restSpring.unitetests.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
    }


    @Test
    void findAll() {

        List<Books> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<BookDTO> books = service.findAll();

        assertNotNull(books);
        assertEquals(14, books.size());

        var book1 =  books.get(1);

        assertNotNull(book1);
        assertNotNull(book1.getId());
        assertNotNull(book1.getLinks());
        assertNotNull(book1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(book1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(book1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("POST")
                )
        );
        assertNotNull(book1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("PUT")
                )
        );
        assertNotNull(book1.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("DELETE")
                )
        );

        assertEquals("author Test1", book1.getAuthor());
        assertEquals(11.00, book1.getPrice());
        assertEquals("title Test1", book1.getTitle());
        assertEquals(Timestamp.valueOf("2026-03-21 23:11:55.443"), book1.getLaunch_date());

        var book4 = books.get(4);

        assertNotNull(book4);
        assertNotNull(book4.getId());
        assertNotNull(book4.getLinks());
        assertNotNull(book4.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(book4.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(book4.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("POST")
                )
        );
        assertNotNull(book4.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("PUT")
                )
        );
        assertNotNull(book4.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("DELETE")
                )
        );

        assertEquals("author Test4", book4.getAuthor());
        assertEquals(14.00, book4.getPrice());
        assertEquals("title Test4", book4.getTitle());
        assertEquals(Timestamp.valueOf("2026-03-21 23:11:55.443"), book4.getLaunch_date());

        var book7 = books.get(7);

        assertNotNull(book7);
        assertNotNull(book7.getId());
        assertNotNull(book7.getLinks());
        assertNotNull(book7.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(book7.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(book7.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("POST")
                )
        );
        assertNotNull(book7.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/person/v1/") &&
                        link.getType().equals("PUT")
                )
        );
        assertNotNull(book7.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("DELETE")
                )
        );

        assertEquals("author Test7", book7.getAuthor());
        assertEquals(17.00, book7.getPrice());
        assertEquals("title Test7", book7.getTitle());
        assertEquals(Timestamp.valueOf("2026-03-21 23:11:55.443"), book7.getLaunch_date());

    }

    @Test
    void findById() {

        Books book = input.mockEntity(1);
        book.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(book));

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

        assertEquals("author Test1", result.getAuthor());
        assertEquals(Double.valueOf(11.00), result.getPrice());
        assertEquals("title Test1", result.getTitle());
        assertEquals(Timestamp.valueOf("2026-03-21 23:11:55.443"), result.getLaunch_date());

    }

    @Test
    void create() {

        Books book = input.mockEntity(1);
        Books persisted = book;
        book.setId(1L);

        BookDTO dto = input.mockDTO(1);


        when(repository.save(book)).thenReturn(persisted);

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

        assertEquals("author Test1", result.getAuthor());
        assertEquals(Double.valueOf(11.00), result.getPrice());
        assertEquals("title Test1", result.getTitle());
        assertEquals(Timestamp.valueOf("2026-03-21 23:11:55.443"), result.getLaunch_date());


    }

    @Test
    void update() {

        Books book = input.mockEntity(1);
        Books persisted = book;
        book.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(repository.save(book)).thenReturn(persisted);

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

        assertEquals("author Test1", result.getAuthor());
        assertEquals(Double.valueOf(11.00), result.getPrice());
        assertEquals("title Test1", result.getTitle());
        assertEquals(Timestamp.valueOf("2026-03-21 23:11:55.443"), result.getLaunch_date());
    }

    @Test
    void delete() {

        Books book = input.mockEntity(1);
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));
        service.delete(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Books.class));
        verifyNoMoreInteractions(repository);

    }
}
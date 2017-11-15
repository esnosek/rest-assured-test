package app.controller;

import app.dto.BookDto;
import app.model.Book;
import app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> create(@RequestBody BookDto bookDto){
        Book book = bookService.create(new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getYear()));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> find(@PathVariable String id){
        Book book = bookService.find(id);
        return book == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> findAuthor(@RequestParam(required = false) String author){
        List<Book> books;
        if(author != null)
            books = bookService.findByAuthor(author);
        else
            books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> put(@PathVariable String id, @RequestBody BookDto bookDto){
        Book book = bookService.put(id, bookDto);
        return book == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> delete(@PathVariable String id){
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

package app.controller;

import app.dto.BookDto;
import app.model.Book;
import app.model.Library;
import app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Library> library(@RequestParam(name = "showBooks", required = false) Boolean showBooks){
        Library library = new Library();
        if(showBooks != null && showBooks) {
            List<Book> books = bookService.findAll();
            library.setBooks(books);
        }
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> create(@RequestBody BookDto bookDto){
        Book book = bookService.create(new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getYear()));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> find(@PathVariable(name = "id") String id){
        Book book = bookService.find(id);
        return book == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> findAuthor(@RequestParam(name = "author", required = false) String author){
        List<Book> books;
        if(author != null)
            books = bookService.findByAuthor(author);
        else
            books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> put(@PathVariable(name = "id") String id, @RequestBody BookDto bookDto){
        Book book = bookService.put(id, bookDto);
        return book == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> delete(@PathVariable(name = "id") String id){
        if(!bookService.exists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> deleteAll(){
        bookService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

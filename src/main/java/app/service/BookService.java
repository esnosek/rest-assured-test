package app.service;

import app.dto.BookDto;
import app.model.Book;
import app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book create(Book book) {
        return bookRepository.insert(book);
    }

    public Book find(String id){
        return bookRepository.findOne(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void delete(String id){
        bookRepository.delete(id);
    }

    public void deleteAll() {
        bookRepository.deleteAll();
    }

    public Book put(String id, BookDto bookDto) {
        if(bookRepository.exists(id))
            return null;
        return bookRepository.insert(new Book(id, bookDto.getTitle(), bookDto.getAuthor(), bookDto.getYear()));

    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByYearGreaterThan(Integer from) {
        return bookRepository.findByYearGreaterThan(from);
    }

    public List<Book> findByYearLessThan(Integer to) {
        return bookRepository.findByYearGreaterThan(to);
    }
}

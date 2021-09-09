package com.galvanize.tmo.paspringstarter;

import com.galvanize.tmo.paspringstarter.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryController {
    List<Book> bookList = new ArrayList<>();

    @GetMapping("/health")
    public String health() {
        return "Hello";
    }

    @PostMapping("/books")
    public ResponseEntity<Book> postBook(@RequestBody Book book) {
        if (book != null) {
            book.setId(bookList.size() + 1);
            bookList.add(book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        if (bookList != null && bookList.size() > 0) {
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/books")
    public ResponseEntity deleteBooks() {
        bookList.removeAll(bookList);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

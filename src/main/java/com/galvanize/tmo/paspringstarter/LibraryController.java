package com.galvanize.tmo.paspringstarter;

import com.galvanize.tmo.paspringstarter.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LibraryController {
    List<Book> bookList = new ArrayList<>();
    HashMap hashMap = new HashMap();
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
    public ResponseEntity<HashMap> getBooks() {
        if (bookList != null && bookList.size() > 0) {
            bookList = bookList.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
        } else {
            bookList = new ArrayList<>();
        }
        hashMap.put("books", bookList);
        return new ResponseEntity<>(hashMap, HttpStatus.OK);
    }

    @DeleteMapping("/books")
    public ResponseEntity deleteBooks() {
        bookList.removeAll(bookList);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

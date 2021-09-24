package com.tubalec.controller;

import com.tubalec.domain.Book;
import com.tubalec.domain.requests.BatchInsertBooksRequest;
import com.tubalec.domain.requests.GetPurchaseByIdRequest;
import com.tubalec.repository.BookDataRepository;
import com.tubalec.service.BookGenerator;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/rest/books")
@RequiredArgsConstructor
public class BookController {

    private final BookDataRepository bookDataRepository;

    private final BookGenerator bookGenerator;

    @ApiOperation(value = "Get all books")
    @GetMapping()
    public Page<Book> getAllBooks() {
        return bookDataRepository.findAll(PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "id")));
    }

    @ApiOperation(value = "Generate books")
    @PostMapping("/batchInsert/{booksCount}")
    public ResponseEntity<String> batchInsertOfBooks(@RequestBody BatchInsertBooksRequest createRequest) {
        List<Book> generateBooks = bookGenerator.generate(createRequest.getCount());
        for (Book b: generateBooks) {
            bookDataRepository.save(b);
        }
        return new ResponseEntity<>("You have successfully insert " + createRequest.getCount() + " books!",
                HttpStatus.OK);
    }
}

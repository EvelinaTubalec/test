package com.tubalec.service;

import com.tubalec.domain.Book;
import com.tubalec.repository.BookDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BookDataRepository bookDataRepository;

    public List<Book> getUserBooksById(Long id) {
        List<Long> bookIdByUserId = bookDataRepository.findBookIdByUserId(id);
        if (bookIdByUserId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Books not found");
        }
        List<Book> userBooks = new ArrayList<>();
        for (Long bookId : bookIdByUserId) {
            Book one = bookDataRepository.findBookById(bookId);
            if (one == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Books not found");
            }
            userBooks.add(one);
        }
        return userBooks;
    }

}

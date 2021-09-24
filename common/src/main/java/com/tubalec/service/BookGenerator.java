package com.tubalec.service;

import com.tubalec.domain.Book;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookGenerator {

    public List<Book> generate(int count) {
        List<Book> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(generate());
        }
        return result;
    }

    public Book generate() {
        Book book = new Book();
        book.setTitle(RandomStringUtils.randomAlphabetic(10));
        book.setNumberOfPages(RandomUtils.nextInt(40, 100));
        book.setAuthor(RandomStringUtils.randomAlphabetic(10));
        book.setPublishingHouse(RandomStringUtils.randomAlphabetic(10));
        book.setGenre(RandomStringUtils.randomAlphabetic(10));
        book.setPrice(RandomUtils.nextFloat(40F, 300F));
        return book;
    }
}

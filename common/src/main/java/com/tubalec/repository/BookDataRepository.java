package com.tubalec.repository;

import com.tubalec.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDataRepository extends CrudRepository<Book, Long>, PagingAndSortingRepository<Book, Long>, JpaRepository<Book, Long> {

    @Query(value = "select b from Book b where b.id = :bookId")
    Book findBookById(@Param("bookId") Long id);

    List<Book> findAllByOrderByIdDesc();

    @Query(value = "select b.price from Book as b where b.id = :bookId")
    Float findBookPriceByBookId(@Param("bookId") Long bookId);

    @Modifying
    @Query(value = "select book_id from Purchase as p inner join Users as u on p.user_id = u.id where u.id = :userId and p.is_deleted = false", nativeQuery = true)
    List <Long> findBookIdByUserId(@Param("userId") Long userId);
}

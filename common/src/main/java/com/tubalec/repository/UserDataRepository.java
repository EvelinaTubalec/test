package com.tubalec.repository;

import com.tubalec.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.annotations.Cacheable;

import java.sql.SQLException;

@Repository
public interface UserDataRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long>, JpaRepository<User, Long> {

    @Cacheable("users")
    User findByLogin(@Param("login") String login);

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = SQLException.class)
    @Modifying
    @Query(value = "update users set money = :userMoney where users.id = :userId", nativeQuery = true)
    void updateUser(@Param("userMoney") Float money, @Param("userId") Long userId);

    @Query(value = "select u.money from User u where u.id = :userId")
    Float findUserMoneyByUserId(@Param("userId") Long id);
}

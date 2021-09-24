package com.tubalec.repository;

import com.tubalec.domain.Purchase;
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

import java.sql.SQLException;
import java.util.List;

@Repository
public interface PurchaseDataRepository extends CrudRepository<Purchase, Long>, PagingAndSortingRepository<Purchase, Long>, JpaRepository<Purchase, Long> {

    @Query(value = "select p from Purchase p where p.id = :purchaseId and p.isDeleted = false and p.userId = :userId")
    Purchase findPurchaseById(@Param("purchaseId") Long purchaseId, @Param("userId") Long userId);

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = SQLException.class)
    @Modifying
    @Query(value = "update Purchase as p set is_deleted = true where p.id = :purchaseId", nativeQuery = true)
    void deleteById(@Param("purchaseId") Long id);

    @Modifying
    @Query(value = "select * from Purchase as p inner join Users as u on p.user_id = u.id where u.id = :userId and p.is_deleted = false", nativeQuery = true)
    List <Purchase> findPurchaseByUserId(@Param("userId") Long userId);

    @Override
    boolean existsById(Long aLong);

    @Query(value = "select p.bookId from Purchase p where p.id = :purchaseId")
    Long findBookIdFromPurchase(@Param("purchaseId") Long id);
}

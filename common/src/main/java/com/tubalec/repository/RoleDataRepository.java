package com.tubalec.repository;

import com.tubalec.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDataRepository extends CrudRepository<Role, Long>, PagingAndSortingRepository<Role, Long>, JpaRepository<Role, Long> {

    @Modifying
    @Query(value = "select r.id as id, r.role_name as role_name from user_roles inner join roles r on r.id = user_roles.role_id where user_roles.user_id = :userId", nativeQuery = true)
    List <Role> findRolesByUserId(@Param("userId") Long userId);
}

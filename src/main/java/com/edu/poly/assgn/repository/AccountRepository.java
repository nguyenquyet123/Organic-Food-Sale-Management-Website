package com.edu.poly.assgn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.poly.assgn.entity.Account;


public interface AccountRepository extends JpaRepository<Account, String> {
    // @Transactional
    // @Modifying
    // @Query("DELETE FROM Customer c WHERE c.username = :username")
    // void deleteByUsername(@Param("username") String name);

    // Account findByUsername(String name);

    // @Query("SELECT a FROM Customer a WHERE a.name LIKE %?1%")
    // Page<Account> findByKeywords(String keywords, Pageable pageable);

    // @Query("SELECT COUNT(a) FROM Customer a WHERE a.username = ?1")
    // int countByUsername(String name);

    // @Transactional
    // @Modifying
    // @Query("INSERT INTO Customer(name) VALUES (:username)")
    // void addCustomer(@Param("username") String name);

    @Query("SELECT COUNT(a) FROM Account a WHERE a.username = ?1 AND a.password = ?2")
    int countByUsernameAndPassword(String username, String password);

    @Query("SELECT a FROM Account a WHERE a.username = ?1")
    Account findByUsername(String username);
}

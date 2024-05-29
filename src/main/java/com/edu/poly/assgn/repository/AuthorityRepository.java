package com.edu.poly.assgn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.poly.assgn.entity.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Integer>{

    @Query("SELECT a FROM Authority a WHERE a.account.username = ?1")
    Optional<Authority> findOneByUsername(String username);

    @Query("SELECT COUNT(a) FROM Authority a WHERE a.account.username = ?1 and a.role.roleId = ?2")
    int checkRole(String username, String role);

    
}

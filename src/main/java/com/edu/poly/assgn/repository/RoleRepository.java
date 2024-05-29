package com.edu.poly.assgn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.poly.assgn.entity.Role;


public interface RoleRepository extends JpaRepository<Role, String>{
    
}

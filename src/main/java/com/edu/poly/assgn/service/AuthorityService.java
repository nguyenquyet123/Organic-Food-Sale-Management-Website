package com.edu.poly.assgn.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.poly.assgn.entity.Authority;
import com.edu.poly.assgn.repository.AuthorityRepository;


@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository authRepository;

    public List<Authority> getAll(){
    return authRepository.findAll();
    }

    public Optional<Authority> getByUsername(String username) {
    return authRepository.findOneByUsername(username);
    }

    public Authority save(Authority authority) {
    return authRepository.save(authority);
    }

    public void deleteById(int id) {
    authRepository.deleteById(id);
    }

    public Boolean isEmpty(int id){
        return authRepository.existsById(id);
    }
}

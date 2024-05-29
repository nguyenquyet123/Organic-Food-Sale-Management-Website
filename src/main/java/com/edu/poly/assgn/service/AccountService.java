package com.edu.poly.assgn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.poly.assgn.entity.Account;
import com.edu.poly.assgn.repository.AccountRepository;


@Service
public class AccountService{

    @Autowired
    private AccountRepository accountRepository;


    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> getById(String username) {
        return accountRepository.findById(username);
    }

    public Account getOneById(String username){
        return accountRepository.findByUsername(username);
    }

    public Account save(Account User) {
        return accountRepository.save(User);
    }

    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }

    public Boolean isEmpty(String id) {
        return accountRepository.existsById(id);
    }

}

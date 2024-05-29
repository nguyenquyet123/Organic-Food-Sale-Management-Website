package com.edu.poly.assgn.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.poly.assgn.entity.Account;
import com.edu.poly.assgn.service.AccountService;


@CrossOrigin("*")
@RestController
public class AccountRestController {
    @Autowired
    private AccountService dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/rest/accounts")
    public ResponseEntity<List<Account>> getALL() {
        return ResponseEntity.ok(dao.getAll()); 
    }

    @GetMapping("/rest/accounts/{username}")
    public ResponseEntity<Account> getOne(@PathVariable String username) {
        if (!dao.isEmpty(username)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dao.getById(username).get());
    }

    @PostMapping("/rest/accounts")
    public ResponseEntity<Account> post(@RequestBody Account account) {
        if (dao.isEmpty(account.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        dao.save(account);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/rest/accounts/{username}")
    public ResponseEntity<Account> put(@PathVariable String username, @RequestBody Account account) {
        if (!dao.isEmpty(username)) {
            return ResponseEntity.notFound().build();
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        dao.save(account);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/rest/accounts/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        if (!dao.isEmpty(username)) {
            return ResponseEntity.notFound().build();
        }
        dao.deleteById(username);
        return ResponseEntity.ok().build();
    }
}

package com.edu.poly.assgn.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.poly.assgn.entity.Authority;
import com.edu.poly.assgn.service.AccountService;
import com.edu.poly.assgn.service.AuthorityService;
import com.edu.poly.assgn.service.RoleService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin("*")
@RestController
public class AuthorityRestController {
    @Autowired
    private AuthorityService authoritySv;

    @Autowired
    private AccountService accountSv;

    @Autowired
    private RoleService roleSv;

    @GetMapping("/rest/authorities")
    public Map<String, Object> getAuthority() {
        Map<String, Object> data = new HashMap<>();
        data.put("authorities", authoritySv.getAll());
        data.put("roles", roleSv.getAll());
        data.put("accounts", accountSv.getAll());
        return data; 
    }

    @PostMapping("/rest/authorities")
    public Authority create(@RequestBody  Authority authority) {
        return authoritySv.save(authority);
    }
    

    @DeleteMapping("/rest/authorities/{id}")
    public void delete(@PathVariable("id") int id){
        authoritySv.deleteById(id);
    }


}

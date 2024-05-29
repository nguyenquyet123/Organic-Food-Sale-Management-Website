package com.edu.poly.assgn.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.edu.poly.assgn.entity.Account;
import com.edu.poly.assgn.service.AccountService;


@Service
@Component
public class AccountDetailService implements UserDetailsService {

    @Autowired
    private AccountService accountService;


    @Override
    public UserDetails loadUserByUsername(String username) throws
    UsernameNotFoundException {
    try {

    Account account = accountService.getOneById(username);
    String password = account.getPassword();
    String[] roles = account.getAuthorities().stream()
    .map(au -> au.getRole().getRoleId())
    .collect(Collectors.toList()).toArray(new String[0]);

    return User.builder().username(username)
                        .password(password)
                        .roles(roles)
                        .build();
    } catch (Exception e) {
    throw new UsernameNotFoundException(username + " NOT FOUND", e);
    }

    }

}





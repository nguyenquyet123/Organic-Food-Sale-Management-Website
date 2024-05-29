package com.edu.poly.assgn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.poly.assgn.entity.Account;
import com.edu.poly.assgn.repository.AccountRepository;
import com.edu.poly.assgn.service.SessionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("shop")
public class RegisterController {
    @Autowired
    SessionService session;

    List<Integer> listProductId;

    String customerName;

    Account customer;

    @Autowired
    AccountRepository dao;

    @GetMapping("register")
    public String register(Model model) {
        listProductId = new ArrayList<>();

        customerName = session.get("customerName", String.class);
        if (customerName == null) {
            customerName = "";
        }

        customer = new Account();

        model.addAttribute("customer", customer);
        model.addAttribute("customerName", customerName);
        model.addAttribute("listProductId", listProductId);
        return "user/register";
    }

    @PostMapping("register")
    public String post(Model model, @RequestParam("action") String action,
            @RequestParam("pass") Optional<String> pass,
            @ModelAttribute("customer") Account customer,
            @ModelAttribute("customer") @Valid Account cus,
            BindingResult errors) {

        switch (action) {
            case "Register":
                if (errors.hasErrors()) {
                    if (pass.isPresent()) {
                        model.addAttribute("error", "Password is not empty");
                    }
                    // model.addAttribute("customer", customer);
                    model.addAttribute("message", "Faild");

                } else {
                    if (containsOnlyDigits(customer.getPhone())) {
                        String pass2 = pass.orElse("");
                        if (customer.getPassword().equals(pass2)) {
                            customer.setRegisterDate((java.sql.Date) new Date());;
                            dao.save(customer);
                            model.addAttribute("message", "Register is Success");
                        } else {
                            model.addAttribute("message", "Faild passwords are not the same");
                        }
                    } else {
                        model.addAttribute("message", "số điện thoại không đúng định dạng");
                    }
                }
                break;
            case "Reset":
                model.addAttribute("customer", new Account());
                break;
            default:
                break;
        }

        listProductId = new ArrayList<>();

        customerName = session.get("customerName", String.class);
        if (customerName == null) {
            customerName = "";
        }

        model.addAttribute("customerName", customerName);
        model.addAttribute("listProductId", listProductId);

        return "user/register";
    }

    public static boolean containsOnlyDigits(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}

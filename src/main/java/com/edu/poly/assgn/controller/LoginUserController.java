// package com.edu.poly.assgn.controller;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.edu.poly.assgn.entity.Account;
// import com.edu.poly.assgn.repository.AccountRepository;
// import com.edu.poly.assgn.service.SessionService;

// import jakarta.validation.Valid;

// @Controller
// @RequestMapping("shop")
// public class LoginUserController {
//     @Autowired
//     SessionService session;

//     @Autowired
//     AccountRepository dao;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     List<Integer> listProductId = new ArrayList<>();;

//     Account customer;

//     String customerName;

//     @GetMapping("login")
//     public String get(Model model) {
//         customerName = session.get("customerName", String.class);
//         if (customerName == null) {
//             customerName = "";
//         }

//         model.addAttribute("customerName", customerName);
//         model.addAttribute("customer", new Account());
//         model.addAttribute("listProductId", listProductId);
//         return "user/login";
//     }

//     @PostMapping("login")
//     public String postAccount(Model model, @RequestParam("action") String action,
//             @ModelAttribute("customer") Account customer,
//             @ModelAttribute("customer.name") @Valid String name,
//             @ModelAttribute("customer.password") @Valid String password,
//             BindingResult errors) {

//         switch (action) {
//             case "Login":
//                 if (errors.hasErrors()) {
//                     model.addAttribute("message", "Faild");
//                 } else {
//                     Account existingCustomer = dao.findByUsername(customer.getUsername());
//                     if (existingCustomer != null
//                             && passwordEncoder.matches(customer.getPassword(), existingCustomer.getPassword())) {
//                         session.set("customerName", customer.getUsername());
//                         return "redirect:/shop/home";
//                     } else {
//                         model.addAttribute("message", "Name or Password is incorrect");
//                     }
//                 }
//                 break;
//             case "Reset":
//                 model.addAttribute("customer", new Account());
//                 break;
//             default:
//                 break;
//         }

//         customerName = session.get("customerName", String.class);
//         if (customerName == null) {
//             customerName = "";
//         }

//         model.addAttribute("customerName", customerName);
//         model.addAttribute("listProductId", listProductId);
//         return "user/login";
//     }
// }

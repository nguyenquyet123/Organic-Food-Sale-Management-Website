// package com.edu.poly.assgn.controller;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.edu.poly.assgn.entity.Account;
// import com.edu.poly.assgn.service.SessionService;

// @Controller
// @RequestMapping("shop")
// public class LogoutUserController {
//     @Autowired
//     SessionService session;

//     List<Integer> listProductId;

//     Account customer;

//     String customerName;

//     @RequestMapping("logout")
//     public String logOut(Model model) {
//         listProductId = new ArrayList<>();
//         customer = new Account();
//         session.remove("customerName");
//         session.remove("listProductId");
//         session.remove("listP");

//         model.addAttribute("customerName", "");
//         model.addAttribute("customer", customer);
//         model.addAttribute("listProductId", listProductId);
//         return "user/login";
//     }

// }

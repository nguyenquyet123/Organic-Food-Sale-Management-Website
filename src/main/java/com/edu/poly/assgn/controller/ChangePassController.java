package com.edu.poly.assgn.controller;
// package com.edu.poly.assgn.userController;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.edu.poly.assgn.dao.AccountDAO;
// import com.edu.poly.assgn.entity.Account;
// import com.edu.poly.assgn.service.SessionService;

// import jakarta.validation.Valid;

// @Controller
// @RequestMapping("shop")
// public class ChangePassController {
//     @Autowired
//     SessionService session;

//     private String customerName;

//     @Autowired
//     AccountDAO dao;

//     Account customer;

//     private List<Integer> getListProductId() {
//         List<Integer> listProductId = session.getList("listProductId");
//         return (listProductId == null) ? new ArrayList<>() : listProductId;
//     }

//     private void addCustomerAndProductAttributes(Model model) {
//         customerName = session.get("customerName", String.class);
//         if (customerName == null) {
//             customerName = "";
//         }
//         model.addAttribute("customerName", customerName);
//         model.addAttribute("listProductId", getListProductId());
//     }

//     @GetMapping("changepass")
//     public String get(Model model) {
//         customerName = session.get("customerName", String.class);
//         if (customerName == null) {
//             return "redirect:/shop/login";
//         }
//         customer = dao.findByUsername(customerName);
//         model.addAttribute("customer", customer);
//         model.addAttribute("customerName", customerName);
//         model.addAttribute("listProductId", getListProductId());
//         return "user/changepass";
//     }

//     @PostMapping("changepass")
//     public String post(Model model, @RequestParam("action") String action,
//             @RequestParam("pass1") String pass1,
//             @RequestParam("pass2") String pass2,
//             @ModelAttribute("customer") Account customerf,
//             @ModelAttribute("customer.name") @Valid String name,
//             @ModelAttribute("customer.password") @Valid String password,
//             BindingResult errors) {
//         addCustomerAndProductAttributes(model);
//         switch (action) {
//             case "Login":
//                 if (errors.hasErrors()) {
//                     model.addAttribute("message", "Faild");
//                 } else {
//                     if (dao.countByUsernameAndPassword(customerf.getUsername(), customerf.getPassword()) > 0) {
//                         if (pass1.equals(pass2)) {
//                             customer = dao.findByUsername(customerf.getUsername());
//                             customer.setPassword(pass1);
//                             dao.save(customer);
//                             model.addAttribute("message", "Success");
//                         } else {
//                             model.addAttribute("message", "Mat khau cu va moi khong giong nhau");
//                         }
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

//         return "user/changepass";
//     }
// }

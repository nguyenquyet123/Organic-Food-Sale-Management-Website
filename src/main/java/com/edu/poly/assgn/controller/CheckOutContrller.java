package com.edu.poly.assgn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.poly.assgn.entity.ATM;
import com.edu.poly.assgn.entity.Account;
import com.edu.poly.assgn.service.AccountService;
// import com.edu.poly.assgn.entity.Bank;
import com.edu.poly.assgn.service.SessionService;

@Controller
@RequestMapping("shop")
public class CheckOutContrller {
    @Autowired
    SessionService session;

    private String customerName;

    @Autowired
    AccountService dao;

    String totalPrice;

    private List<Integer> getListProductId() {
        List<Integer> listProductId = session.getList("listProductId");
        return (listProductId == null) ? new ArrayList<>() : listProductId;
    }

    private void addCustomerAndProductAttributes(Model model) {
        customerName = session.get("customerName", String.class);
        if (customerName == null) {
            customerName = "";
        }
        model.addAttribute("customerName", customerName);
        model.addAttribute("listProductId", getListProductId());
    }

    @GetMapping("checkout")
    public String get(Model model) {
        addCustomerAndProductAttributes(model);

        // if (customerName != null) {
        //     Account customer = dao.getOneById(customerName);
        //     model.addAttribute("customer", customer);
        // }else{
        //     model.addAttribute("customer", new Account());
        // }

        Account customer = new Account();

        if (customerName != "") {
            customer = dao.getOneById(customerName);
        }

        model.addAttribute("customer", customer);
        // model.addAttribute("customer", new Account());
        model.addAttribute("address", "");
        model.addAttribute("message", "");

        totalPrice = session.get("totalPrice", String.class);
        // model.addAttribute("totalPrice", totalPrice);
        // model.addAttribute("atm", new ATM());
        // model.addAttribute("bank", Bank.list);
        return "user/checkout";
    }

    @PostMapping("checkout")
    public String post(Model model, @ModelAttribute("atm") ATM atm, @RequestParam("address") String address) {
        // addCustomerAndProductAttributes(model);
        // if (atm != null) {
        //     model.addAttribute("message", "Thanh toán thành công");
        // }else{
        //     model.addAttribute("message", "Thông tin không hợp lệ");
        // }

        // totalPrice = session.get("totalPrice", String.class);
        // model.addAttribute("totalPrice", totalPrice);

        addCustomerAndProductAttributes(model);

        Account customer = dao.getOneById(customerName);

        model.addAttribute("customer", customer);
        model.addAttribute("address", address);
        model.addAttribute("message", "Order Success");
        // model.addAttribute("atm", atm);
        // model.addAttribute("bank", Bank.list);
        return "user/checkout";
    }
    
}

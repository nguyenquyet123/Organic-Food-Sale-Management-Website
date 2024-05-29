package com.edu.poly.assgn.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.edu.poly.assgn.service.SessionService;
// import com.nimbusds.jose.shaded.gson.Gson;

@Controller
public class IndexShopPageController {

    @Autowired
    SessionService session;

    private String customerName;

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


    // @RequestMapping("/shop/admin")
    // public String adminHome(Model model) {
    //     addCustomerAndProductAttributes(model);
    //     return "redirect:/spa/index.html";
    // }

    @RequestMapping("/shop/admin")
    public String adminHome(Model model) {
        addCustomerAndProductAttributes(model);
        return "admin/index";
    }

    @RequestMapping("/shop/login")
    public String login(Model model) {

        addCustomerAndProductAttributes(model);
        model.addAttribute("message", "");
        return "user/login";
    }

    @RequestMapping("/shop/fail")
    public String loginFail(Model model) {

        addCustomerAndProductAttributes(model);
        model.addAttribute("message", "Username or password incorrect");
        return "user/login";
    }

    @RequestMapping("/shop/cart")
    public String cart(Model model) {

        addCustomerAndProductAttributes(model);
        return "user/cart";
    }

    @RequestMapping("/shop/about")
    public String about(Model model) {
        addCustomerAndProductAttributes(model);
        return "user/about";
    }

    @RequestMapping("/shop/blog-single")
    public String blogSingle(Model model) {
        addCustomerAndProductAttributes(model);
        return "user/blog-single";
    }

    @RequestMapping("/shop/blog")
    public String blog(Model model) {
        addCustomerAndProductAttributes(model);
        return "user/blog";
    }

    @RequestMapping("/shop/wishlist")
    public String wishList(Model model) {
        addCustomerAndProductAttributes(model);
        return "user/wishlist";
    }

}

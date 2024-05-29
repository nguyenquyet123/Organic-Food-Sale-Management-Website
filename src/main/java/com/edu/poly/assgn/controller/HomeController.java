package com.edu.poly.assgn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.poly.assgn.entity.Category;
import com.edu.poly.assgn.entity.Account;
import com.edu.poly.assgn.entity.Product;
import com.edu.poly.assgn.repository.CategoryRepository;
import com.edu.poly.assgn.repository.ProductRepository;
import com.edu.poly.assgn.service.SessionService;

import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("shop")
public class HomeController {
    @Autowired
    CategoryRepository cDao;

    @Autowired
    ProductRepository pDao;

    @Autowired
    SessionService session;

    List<Category> listCategory;

    List<Product> listDiscount;

    List<Integer> listProductId;

    String customerName;

    @RequestMapping("home")
    public String get(Model model) {
        listCategory = cDao.findAll();
        listDiscount = pDao.findTop4ProductsByDiscount();
        listProductId = session.getList("listProductId");
        if (listProductId == null) {
            listProductId = new ArrayList<>();
        }

        customerName = session.get("customerName", String.class);
        if (customerName == null) {
            customerName = "";
        }

        model.addAttribute("customerName", customerName);
        model.addAttribute("listProductId", listProductId);
        model.addAttribute("category", listCategory);
        model.addAttribute("dicount", listDiscount);
        return "user/index";
    }

    @RequestMapping("home/select/{id}")
    public String addToCart(Model model, @PathVariable("id") int id) {
        customerName = session.get("customerName", String.class);
        if (customerName == null) {
            customerName = "";
            listProductId = new ArrayList<>();
            model.addAttribute("customerName", customerName);
            model.addAttribute("listProductId", listProductId);
            model.addAttribute("customer", new Account());
            return "user/login";
        }

        listCategory = cDao.findAll();
        listDiscount = pDao.findTop4ProductsByDiscount();

        listProductId = session.getList("listProductId");
        if (listProductId == null) {
            listProductId = new ArrayList<>();
            listProductId.add(id);
        } else {
            if (!containsValue(listProductId, id)) {
                listProductId.add(id);
            }
        }
        session.set("listProductId", listProductId);

        customerName = session.get("customerName", String.class);
        if (customerName == null) {
            customerName = "";
        }

        model.addAttribute("customerName", customerName);

        model.addAttribute("listProductId", listProductId);
        model.addAttribute("category", listCategory);
        model.addAttribute("dicount", listDiscount);
        model.addAttribute("showMessage", true);
        return "user/index";
    }

    public static boolean containsValue(List<Integer> list, int value) {
        for (int num : list) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }
}

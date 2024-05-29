package com.edu.poly.assgn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.poly.assgn.entity.Category;
import com.edu.poly.assgn.entity.Account;
import com.edu.poly.assgn.entity.Product;
import com.edu.poly.assgn.repository.CategoryRepository;
import com.edu.poly.assgn.repository.ProductRepository;
import com.edu.poly.assgn.service.SessionService;

@Controller
@RequestMapping("shop")
public class ShopController {
    @Autowired
    CategoryRepository cDao;

    @Autowired
    ProductRepository pDao;

    List<Category> listCategory;

    Page<Product> page;

    @Autowired
    SessionService session;

    List<Integer> listProductId;

    String customerName;

    @RequestMapping("shop")
    public String get(Model model) {
        listCategory = cDao.findAll();
        Pageable pageable = PageRequest.of(0, 4);
        page = pDao.findAll(pageable);

        List<Integer> listProductId = session.getList("listProductId");
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
        model.addAttribute("page", page);
        model.addAttribute("selectedTab", "all");
        return "user/shop";
    }

    @RequestMapping("shop/pageable")
    public String shopAll(Model model, @RequestParam("p") Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 4);
        page = pDao.findAll(pageable);
        listCategory = cDao.findAll();

        List<Integer> listProductId = session.getList("listProductId");
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
        model.addAttribute("page", page);
        model.addAttribute("selectedTab", "all");
        return "user/shop";
    }

    @RequestMapping("shop/pageablefruits/{id}")
    public String shopFruits(Model model, @PathVariable("id") int id) {

        Pageable pageable = PageRequest.of(0, 4);
        page = pDao.findProductsByCategoryId(id, pageable);
        listCategory = cDao.findAll();

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
        model.addAttribute("page", page);
        model.addAttribute("selectedCategoryId", id);
        model.addAttribute("selectedTab", "");
        return "user/shop";
    }

    @RequestMapping("shop/select/{id}")
    public String select(Model model, @PathVariable("id") int id) {
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
        Pageable pageable = PageRequest.of(0, 4);
        page = pDao.findAll(pageable);

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

        model.addAttribute("showMessage", true);
        model.addAttribute("listProductId", listProductId);
        model.addAttribute("category", listCategory);
        model.addAttribute("page", page);
        model.addAttribute("selectedTab", "all");
        return "user/shop";
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

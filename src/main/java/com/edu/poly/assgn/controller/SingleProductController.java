package com.edu.poly.assgn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.poly.assgn.entity.Account;
import com.edu.poly.assgn.entity.Product;
import com.edu.poly.assgn.repository.ProductRepository;
import com.edu.poly.assgn.service.SessionService;

@Controller
@RequestMapping("shop")
public class SingleProductController {
    @Autowired
    ProductRepository pDao;

    @Autowired
    SessionService session;

    List<Integer> listProductId;

    Optional<Product> pro;

    Product product;

    List<Product> listProduct;

    Page<Product> page;

    String customerName;

    @RequestMapping("product-single")
    public String productSingle(Model model) {
        listProduct = pDao.findProductsListByCategoryId(1);

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
        model.addAttribute("item", new Product());
        model.addAttribute("list", listProduct);
        return "user/product-single";
    }

    @RequestMapping("product-single/detail/{id}")
    public String productSingleId(Model model, @PathVariable("id") int id) {

        listProduct = pDao.findProductsListByCategoryId(pDao.findCategoryId(id));
        pro = pDao.findById(id);
        product = pro.orElse(new Product());

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
        model.addAttribute("list", listProduct);
        model.addAttribute("item", product);
        return "user/product-single";
    }

    @RequestMapping("product-single/select/{id}")
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
        if (id == 0) {
            return "redirect:/shop/product-single";
        }
        listProduct = pDao.findProductsListByCategoryId(pDao.findCategoryId(id));
        pro = pDao.findById(id);
        product = pro.orElse(new Product());

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

        model.addAttribute("listProductId", listProductId);
        model.addAttribute("showMessage", true);
        model.addAttribute("list", listProduct);
        model.addAttribute("item", product);
        return "user/product-single";
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

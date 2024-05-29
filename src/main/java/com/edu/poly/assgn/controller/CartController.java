// package com.edu.poly.assgn.controller;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.edu.poly.assgn.entity.Product;
// import com.edu.poly.assgn.repository.ProductRepository;
// import com.edu.poly.assgn.service.SessionService;
// import com.edu.poly.assgn.service.ShoppingCardService;

// @Controller
// @RequestMapping("shop")
// public class CartController {
//     @Autowired
//     SessionService session;

//     @Autowired
//     ProductRepository dao;

//     @Autowired
//     ShoppingCardService sv;

//     String customerName;

//     List<Product> listP;

//     List<Integer> listProductId;

//     static Map<Integer, Product> items = new HashMap<>();

//     float totalPrice;

//     float subTotal;

//     float discount;

//     private void prepareModel(Model model) {
//         listP = session.getListProduct("listP");
//         listProductId = session.getList("listProductId");
//         if (listP != null && listP.size() == listProductId.size()) {
//             for (Product product : listP) {
//                 items.put(product.getProductId(), product);
//             }
//         } else {

//             listP = new ArrayList<>();
//             if (listProductId == null) {
//                 listProductId = new ArrayList<>();
//                 customerName = "";
//             } else {
//                 for (Integer productId : listProductId) {
//                     listP.add(dao.findProduct(productId));
//                 }
//                 session.set("listP", listP);
//                 for (Product product : listP) {
//                     items.put(product.getProductId(), product);
//                 }
//             }

//         }
//         customerName = session.get("customerName", String.class);
//         if (customerName == null) {
//             customerName = "";
//         }
//         model.addAttribute("listProductId", listProductId);
//         model.addAttribute("customerName", customerName);
//         model.addAttribute("items", items);
//     }

//     @RequestMapping("cart")
//     public String cart(Model model) {
//         prepareModel(model);
//         subTotal = 0;
//         discount = 0;
//         for (Product product : listP) {
//             subTotal += product.getUnitPrice() * product.getQuantity();
//             discount += (product.getDiscount() * product.getUnitPrice())*product.getQuantity();
//         }
//         totalPrice = subTotal - discount;

//         session.set("totalPrice", String.valueOf(totalPrice));

//         model.addAttribute("subTotal", subTotal);
//         model.addAttribute("discount", discount);
//         model.addAttribute("totalPrice", totalPrice);
//         return "user/cart";
//     }

//     @RequestMapping("cart/form/{id}")
//     public String form(Model model,
//             @PathVariable("id") Integer id,
//             @RequestParam("quantity") int quantity) {
//         prepareModel(model);
//         sv = new ShoppingCardService(items);
//         sv.update(id, quantity);
//         subTotal = 0;
//         discount = 0;
//         for (Product product : listP) {
//             subTotal += product.getUnitPrice() * product.getQuantity();
//             discount += (product.getDiscount() * product.getUnitPrice())*product.getQuantity();
//         }
//         totalPrice = subTotal - discount;

//         session.set("totalPrice", String.valueOf(totalPrice));

//         model.addAttribute("subTotal", subTotal);
//         model.addAttribute("discount", discount);
//         model.addAttribute("totalPrice", totalPrice);
//         return "user/cart";
//     }

//     @RequestMapping("cart/form/remove/{id}")
//     public String remove(Model model,
//             @PathVariable("id") Integer id) {
//         listP = session.getListProduct("listP");
//         List<Product> productsToRemove = new ArrayList<>();
//         for (Product product : listP) {
//             if (product.getProductId() == id) {
//                 productsToRemove.add(product);
//             }
//         }
//         listP.removeAll(productsToRemove);
//         session.set("listP", listP);

//         listProductId = session.getList("listProductId");
//         List<Integer> list = new ArrayList<>();
//         for (Integer i : listProductId) {
//             if (i == id) {
//                 list.add(i);
//             }
//         }
//         listProductId.removeAll(list);
//         session.set("listProductId", listProductId);

//         prepareModel(model);
//         sv = new ShoppingCardService(items);
//         sv.remove(id);
//         subTotal = 0;
//         discount = 0;
//         for (Product product : listP) {
//             subTotal += product.getUnitPrice() * product.getQuantity();
//             discount += (product.getDiscount() * product.getUnitPrice())*product.getQuantity();
//         }
//         totalPrice = subTotal - discount;

//         session.set("totalPrice", String.valueOf(totalPrice));

//         model.addAttribute("subTotal", subTotal);
//         model.addAttribute("discount", discount);
//         model.addAttribute("totalPrice", totalPrice);
//         return "user/cart";
//     }

//     @RequestMapping("cart/form/clear")
//     public String clear(Model model) {
//         prepareModel(model);
//         sv = new ShoppingCardService(items);
//         sv.clear();
//         session.remove("listP");
//         session.remove("listProductId");
//         listProductId = new ArrayList<>();

//         session.set("totalPrice", "0");

//         model.addAttribute("listProductId", listProductId);
//         model.addAttribute("totalPrice", 0);
//         model.addAttribute("subTotal", 0);
//         model.addAttribute("discount", 0);
//         return "user/cart";
//     }
// }

package com.edu.poly.assgn.restcontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.poly.assgn.entity.Order;
import com.edu.poly.assgn.service.OrderService;

@CrossOrigin("*")
@RestController
public class OrderRestController {

    @Autowired
    private OrderService dao;

    @GetMapping("/rest/orders")
    public ResponseEntity<List<Order>> getALL() {
        return ResponseEntity.ok(dao.getAll()); 
    }

    @GetMapping("/rest/orders/{id}")
    public ResponseEntity<Order> getOne(@PathVariable int id) {
        if (!dao.isEmpty(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dao.getById(null).orElse(null));
    }

    @PostMapping("/rest/orders")
    public ResponseEntity<Order> post(@RequestBody Order order) {
        if (dao.isEmpty(order.getOrderId())) {
            return ResponseEntity.badRequest().build();
        }
        order.setOrderDate(new Date());
        dao.save(order);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/rest/orders/{orderId}")
    public ResponseEntity<Order> put(@PathVariable int orderId, @RequestBody Order order) {
        if (!dao.isEmpty(orderId)) {
            return ResponseEntity.notFound().build();
        }
        dao.save(order);
        return ResponseEntity.ok(order);
    }


    @DeleteMapping("/rest/orders/{orderId}")
    public ResponseEntity<Void> delete(@PathVariable int orderId) {
        if (!dao.isEmpty(orderId)) {
            return ResponseEntity.notFound().build();
        }
        dao.deleteById(orderId);
        return ResponseEntity.ok().build();
    }
    
}

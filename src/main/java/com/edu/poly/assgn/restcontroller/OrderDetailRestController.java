package com.edu.poly.assgn.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.edu.poly.assgn.entity.OrderDetail;
import com.edu.poly.assgn.service.OrderDetailService;

@CrossOrigin("*")
@RestController
public class OrderDetailRestController {

    @Autowired
    private OrderDetailService dao;

    @GetMapping("/rest/orderdetails")
    public ResponseEntity<List<OrderDetail>> getALL() {
        return ResponseEntity.ok(dao.getAll()); 
    }

    @GetMapping("/rest/orderdetails/{id}")
    public ResponseEntity<OrderDetail> getOne(@PathVariable int id) {
        if (!dao.isEmpty(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dao.getById(null).orElse(null));
    }

    @GetMapping("/rest/orderdetails/all/{name}")
    public ResponseEntity<List<OrderDetail>> getAllByName(@PathVariable String name) {

        return ResponseEntity.ok(dao.findAllByKeywords(name));
    }

    // @PostMapping("/rest/orderdetails")
    // public ResponseEntity<OrderDetail> post(@RequestBody OrderDetail order) {
    //     if (dao.isEmpty(order.getOrderDetailId())) {
    //         return ResponseEntity.badRequest().build();
    //     }
    //     dao.save(order);
    //     return ResponseEntity.ok(order);
    // }

    // @PutMapping("/rest/orderdetails/{orderDetailId}")
    // public ResponseEntity<OrderDetail> put(@PathVariable int orderId, @RequestBody OrderDetail order) {
    //     if (!dao.isEmpty(orderId)) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     dao.save(order);
    //     return ResponseEntity.ok(order);
    // }


    // @DeleteMapping("/rest/orderdetails/{orderId}")
    // public ResponseEntity<Void> delete(@PathVariable int orderId) {
    //     if (!dao.isEmpty(orderId)) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     dao.deleteById(orderId);
    //     return ResponseEntity.ok().build();
    // }
    
}

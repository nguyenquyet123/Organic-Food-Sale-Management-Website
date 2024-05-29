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

import com.edu.poly.assgn.entity.Product;
import com.edu.poly.assgn.service.ProductService;

@CrossOrigin("*")
@RestController
public class ProductRestController {
    @Autowired
    private ProductService dao;

    @GetMapping("/rest/products")
    public ResponseEntity<List<Product>> getALL() {
        return ResponseEntity.ok(dao.getAllProducts()); 
    }

    @GetMapping("/rest/products/{id}")
    public ResponseEntity<Product> getOne(@PathVariable int id) {
        if (!dao.isEmpty(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dao.getProductById(id).orElse(null));
    }

    @PostMapping("/rest/products")
    public ResponseEntity<Product> post(@RequestBody Product product) {
        if (dao.isEmpty(product.getProductId())) {
            return ResponseEntity.badRequest().build();
        }
        product.setEnteredDate(new Date());
        dao.saveProduct(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/rest/products/{productId}")
    public ResponseEntity<Product> put(@PathVariable int productId, @RequestBody Product product) {
        if (!dao.isEmpty(productId)) {
            return ResponseEntity.notFound().build();
        }
        dao.saveProduct(product);
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/rest/products/{productId}")
    public ResponseEntity<Void> delete(@PathVariable int productId) {
        if (!dao.isEmpty(productId)) {
            return ResponseEntity.notFound().build();
        }
        dao.deleteProductById(productId);;
        return ResponseEntity.ok().build();
    }

    

    
}

package com.edu.poly.assgn.restcontroller;

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

import com.edu.poly.assgn.entity.Category;
import com.edu.poly.assgn.service.CategoryService;

@CrossOrigin("*")
@RestController
public class CategoryRestController {

    @Autowired
    private CategoryService dao;

    @GetMapping("/rest/categorys")
    public ResponseEntity<List<Category>> getALL() {
        return ResponseEntity.ok(dao.getAll()); 
    }

    @GetMapping("/rest/categorys/{id}")
    public ResponseEntity<Category> getOne(@PathVariable int id) {
        if (!dao.isEmpty(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dao.getById(id).orElse(null));
    }

    // @GetMapping("/rest/categorys/by/{name}")
    // public ResponseEntity<Category> getByName(@PathVariable String name) {
    //     if (!dao.isEmpty(id)) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok(dao.getByName(name));
    // }

    @PostMapping("/rest/categorys")
    public ResponseEntity<Category> post(@RequestBody Category category) {
        if (dao.isEmpty(category.getCategoryId())) {
            return ResponseEntity.badRequest().build();
        }
        dao.save(category);
        return ResponseEntity.ok(category);
    }
    

    @PutMapping("/rest/categorys/{categoryId}")
    public ResponseEntity<Category> put(@PathVariable int categoryId, @RequestBody Category category) {
        if (!dao.isEmpty(categoryId)) {
            return ResponseEntity.notFound().build();
        }
        dao.save(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/rest/categorys/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable int categoryId) {
        if (!dao.isEmpty(categoryId)) {
            return ResponseEntity.notFound().build();
        }
        dao.deleteById(categoryId);
        return ResponseEntity.ok().build();
    }

    // @DeleteMapping("/rest/categorys/{name}")
    // public ResponseEntity<Void> delete(@PathVariable String name) {
    //     dao.deleteByName(name);
    //     return ResponseEntity.ok().build();
    // }
    
}

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

import com.edu.poly.assgn.entity.Role;
import com.edu.poly.assgn.service.RoleService;


@CrossOrigin("*")
@RestController
public class RoleRestController {
    @Autowired
    private RoleService dao;

    @GetMapping("/rest/roles")
    public ResponseEntity<List<Role>> getALL() {
        return ResponseEntity.ok(dao.getAll());
    }

    @GetMapping("/rest/roles/{roleId}")
    public ResponseEntity<Role> getOne(@PathVariable String roleId) {
        if (!dao.isEmpty(roleId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dao.getById(roleId).get());
    }

    @PostMapping("/rest/roles")
    public ResponseEntity<Role> post(@RequestBody Role Role) {
        if (dao.isEmpty(Role.getRoleId())) {
            return ResponseEntity.badRequest().build();
        }
        dao.save(Role);
        return ResponseEntity.ok(Role);
    }

    @PutMapping("/rest/roles/{roleId}")
    public ResponseEntity<Role> put(@PathVariable String roleId, @RequestBody Role Role) {
        if (!dao.isEmpty(roleId)) {
            return ResponseEntity.notFound().build();
        }
        dao.save(Role);
        return ResponseEntity.ok(Role);
    }

    @DeleteMapping("/rest/roles/{roleId}")
    public ResponseEntity<Void> delete(@PathVariable String roleId) {
        if (!dao.isEmpty(roleId)) {
            return ResponseEntity.notFound().build();
        }
        dao.deleteById(roleId);
        return ResponseEntity.ok().build();
    }

}

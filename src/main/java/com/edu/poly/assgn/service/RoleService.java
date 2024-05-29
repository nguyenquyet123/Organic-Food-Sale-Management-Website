package com.edu.poly.assgn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.poly.assgn.entity.Role;
import com.edu.poly.assgn.repository.RoleRepository;


@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> getById(String Rolename) {
        return roleRepository.findById(Rolename);
    }

    public Role save(Role Role) {
        return roleRepository.save(Role);
    }

    public void deleteById(String id) {
        roleRepository.deleteById(id);
    }

    public Boolean isEmpty(String id) {
        return roleRepository.existsById(id);
    }
}

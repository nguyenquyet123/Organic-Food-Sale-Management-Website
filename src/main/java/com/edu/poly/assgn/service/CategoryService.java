package com.edu.poly.assgn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.poly.assgn.entity.Category;
import com.edu.poly.assgn.repository.CategoryRepository;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getById(Integer id) {
        return categoryRepository.findById(id);
    }

    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        categoryRepository.deleteByName(name);
    }

    public Boolean isEmpty(int id) {
        return categoryRepository.existsById(id);
    }

}

package com.edu.poly.assgn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.poly.assgn.entity.Category;

import jakarta.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    void deleteByName(String name);

    Category findByName(String name);

    @Query("SELECT a FROM Category a WHERE a.name LIKE %?1%")
    Page<Category> findByKeywords(String keywords, Pageable pageable);

    @Query("SELECT COUNT(a) FROM Category a WHERE a.name = ?1")
    int countByName(String name);

    // @Transactional
    // @Modifying
    // @Query("INSERT INTO Category(name) VALUES (:name)")
    // void addCategory(@Param("name") String name);

    @Transactional
    default void addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        save(category);
    }
}

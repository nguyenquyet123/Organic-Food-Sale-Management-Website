package com.edu.poly.assgn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.poly.assgn.entity.Product;
import com.edu.poly.assgn.entity.Report;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p ORDER BY p.productId DESC")
    List<Product> findAllOrderById();

    @Query("SELECT p FROM Product p ORDER BY p.name DESC")
    List<Product> findAllOrderByName();

    @Query("SELECT p FROM Product p ORDER BY p.unitPrice DESC")
    List<Product> findAllOrderByPrice();

    @Query("SELECT p FROM Product p ORDER BY p.enteredDate DESC")
    List<Product> findAllOrderByDate();

    @Query("SELECT p FROM Product p WHERE p.productId BETWEEN ?1 AND ?2 ORDER BY p.productId DESC")
    List<Product> findProductsInIdRange(int n1, int n2);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    Page<Product> findByKeywords(String keywords, Pageable pageable);

    @Query("SELECT new com.edu.poly.assgn.entity.Report(o.category, sum(o.unitPrice), count(o)) "
            + "FROM Product o "
            + "GROUP BY o.category "
            + "ORDER BY sum(o.unitPrice) DESC")
    List<Report> getInventoryByCategory();

    List<Product> findByunitPriceBetween(double n1, double n2);

    Page<Product> findAllBynameLike(String keywords, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.discount > 0 ORDER BY p.discount DESC LIMIT 4")
    List<Product> findTop4ProductsByDiscount();

    @Query("SELECT p FROM Product p WHERE p.category.categoryId = ?1")
    Page<Product> findProductsByCategoryId(int keywords, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.categoryId = ?1")
    List<Product> findProductsListByCategoryId(int keywords);

    @Query("SELECT p.category.categoryId FROM Product p WHERE p.productId = ?1")
    int findCategoryId(int keywords);    

    @Query("SELECT p FROM Product p WHERE p.productId = ?1")
    Product findProduct(int id);

    void deleteByName(String name);

    Product findByName(String name);

}

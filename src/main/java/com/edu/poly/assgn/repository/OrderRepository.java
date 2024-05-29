package com.edu.poly.assgn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.poly.assgn.entity.Order;
import com.edu.poly.assgn.entity.OrderDetail;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    
    Page<Order> findByTotalPriceGreaterThanEqualAndTotalPriceLessThanEqual(float minTotalPrice, float maxTotalPrice, Pageable pageable);

    // @Modifying
    // @Transactional
    // @Query("DELETE FROM Order o WHERE o.account.username = :username")
    // void deleteByCustomerCustomerId(String username);

    @Query("SELECT a FROM OrderDetail a WHERE a.product.category.name LIKE %?1%")
    List<OrderDetail> findByKeywords(String keywords);

}

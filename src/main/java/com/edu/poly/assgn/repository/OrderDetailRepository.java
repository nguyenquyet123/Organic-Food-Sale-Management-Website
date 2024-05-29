package com.edu.poly.assgn.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.poly.assgn.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query("SELECT a FROM OrderDetail a WHERE a.product.category.name LIKE %?1%")
    List<OrderDetail> findByKeywords(String keywords);

    @Query("SELECT SUM(a.quantity * a.unitPrice) FROM OrderDetail a WHERE a.product.category.name LIKE %?1%")
    float findTotalRevenueByCategory(String keywords);

    @Query("SELECT SUM(a.quantity * a.unitPrice) FROM OrderDetail a")
    float RevenueByCategory();

    @Query("SELECT SUM(a.quantity * a.unitPrice) FROM OrderDetail a WHERE a.order.orderDate >= ?1 AND a.order.orderDate <= ?2")
    float RevenueByTime(Timestamp startTimestamp, Timestamp endTimestamp);
    
    @Query("SELECT a FROM OrderDetail a WHERE a.order.orderDate >= ?1 AND a.order.orderDate <= ?2")
    Page<OrderDetail> RevenueByTimePage(Timestamp startTimestamp, Timestamp endTimestamp, Pageable pageable);


}

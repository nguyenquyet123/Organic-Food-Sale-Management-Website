package com.edu.poly.assgn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.poly.assgn.entity.OrderDetail;
import com.edu.poly.assgn.repository.OrderDetailRepository;


@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    public Optional<OrderDetail> getById(Integer id) {
        return orderDetailRepository.findById(id);
    }

    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteById(Integer id) {
        orderDetailRepository.deleteById(id);
    }

    public Boolean isEmpty(int id){
        return orderDetailRepository.existsById(id);
    }

    public List<OrderDetail> findAllByKeywords(String name){
        return orderDetailRepository.findByKeywords(name);
    }

}

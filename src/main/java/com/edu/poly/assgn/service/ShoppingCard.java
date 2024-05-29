package com.edu.poly.assgn.service;

import java.util.List;

import com.edu.poly.assgn.entity.Product;

public interface ShoppingCard {
    List<Product> getListItems();

    void add(int id);

    void remove(int id);

    void update(int id);

    void update(int id, int qty);

    void clear();

    int getCount();

    double getAmount();
}

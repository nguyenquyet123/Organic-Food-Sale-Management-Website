package com.edu.poly.assgn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.edu.poly.assgn.entity.Product;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SessionScope
public class ShoppingCardService implements ShoppingCard {

    private Map<Integer, Product> map;

    public ShoppingCardService(Map<Integer, Product> map) {
        this.map = map;
    }

    @Override
    public void update(int id, int qty) {
        Product cardItem = map.get(id);
        if (cardItem != null) {
            cardItem.setQuantity(qty);
            map.put(id, cardItem);
        }
    }

    @Override
    public List<Product> getListItems() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void add(int id) {
        Product cardItem = map.getOrDefault(id, new Product());
        cardItem.setQuantity(cardItem.getQuantity() + 1);
        map.put(id, cardItem);
    }

    @Override
    public void remove(int id) {
        map.remove(id);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int getCount() {
        return map.values().stream().mapToInt(Product::getQuantity).sum();
    }

    @Override
    public double getAmount() {
        return map.values().stream().mapToDouble(cardItem -> cardItem.getUnitPrice() * cardItem.getQuantity()).sum();
    }

    @Override
    public void update(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}

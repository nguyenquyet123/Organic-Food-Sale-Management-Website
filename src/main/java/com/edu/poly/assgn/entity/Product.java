package com.edu.poly.assgn.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Table(name = "products")
@Data
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id") 
    private int productId;

    @ManyToOne
    @JoinColumn(name = "category_id") 
    @NotNull(message = "CategoryId is not empty")
    private Category category;

    @NotBlank(message = "Name is not empty")
    private String name;

    @NotNull(message = "Quantity is not empty")
    private int quantity;

    @NotNull(message = "UnitPrice is not empty")
    private float unitPrice;
    private String image;
    private String description;
    
    @Column(name = "entered_date") 
    private Date enteredDate;
    
    private String status;

    @NotNull(message = "Discount is not empty")
    private float discount;

    

}

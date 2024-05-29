package com.edu.poly.assgn.entity;

import lombok.Data;

@Data
public class EmailForm {
    private String to;
    private String subject;
    private String message;
}

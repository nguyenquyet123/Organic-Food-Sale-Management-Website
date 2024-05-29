package com.edu.poly.assgn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {

    @Autowired
    private HttpServletRequest request;

    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return (value != null) ? value : defaultValue;
    }

    public int getInt(String name, int defaultValue) {
        String value = request.getParameter(name);
        return (value != null) ? Integer.parseInt(value) : defaultValue;
    }

    public double getDouble(String name, double defaultValue) {
        String value = request.getParameter(name);
        return (value != null) ? Double.parseDouble(value) : defaultValue;
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
    }

    public Date getDate(String name, String pattern) {
        String value = request.getParameter(name);
        if (value != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            try {
                return dateFormat.parse(value);
            } catch (ParseException e) {
                throw new RuntimeException("Invalid date format");
            }
        }
        return null;
    }

    public File save(MultipartFile file, String path) {
        if (!file.isEmpty()) {
            try {
                String fullPath = path + File.separator + file.getOriginalFilename();
                File savedFile = new File(fullPath);
                file.transferTo(savedFile);
                return savedFile;
            } catch (IOException e) {
                throw new RuntimeException("Failed to save file");
            }
        }
        return null;
    }
}

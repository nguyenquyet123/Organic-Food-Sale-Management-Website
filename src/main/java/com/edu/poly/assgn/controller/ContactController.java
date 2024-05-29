package com.edu.poly.assgn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.poly.assgn.entity.EmailForm;
import com.edu.poly.assgn.service.SessionService;


@Controller
@RequestMapping("shop")
public class ContactController {
    @Autowired
    SessionService session;

    private String customerName;

    // @Autowired
    // private JavaMailSender mailSender;

    private List<Integer> getListProductId() {
        List<Integer> listProductId = session.getList("listProductId");
        return (listProductId == null) ? new ArrayList<>() : listProductId;
    }

    private void addCustomerAndProductAttributes(Model model) {
        customerName = session.get("customerName", String.class);
        if (customerName == null) {
            customerName = "";
        }
        model.addAttribute("customerName", customerName);
        model.addAttribute("listProductId", getListProductId());
    }

    @GetMapping("contact")
    public String get(Model model) {
        addCustomerAndProductAttributes(model);
        model.addAttribute("emailForm", new EmailForm());
        return "user/contact";
    }

    // @PostMapping("contact")
    // public String post(Model model, @ModelAttribute("emailForm") EmailForm emailForm) {
    //     addCustomerAndProductAttributes(model);

    //     try {
    //         MimeMessage message = mailSender.createMimeMessage();
    //         MimeMessageHelper helper = new MimeMessageHelper(message, true);
    //         helper.setTo(emailForm.getTo());
    //         helper.setSubject(emailForm.getSubject());
    //         helper.setText(emailForm.getMessage(), true);
    //         mailSender.send(message);
    //         model.addAttribute("message", "Email sent successfully!");
    //     } catch (MessagingException e) {
    //         model.addAttribute("error", "Error sending email: " + e.getMessage());
    //     }

    //     model.addAttribute("emailForm", emailForm);
    //     return "user/contact";
    // }
}

// package com.edu.poly.assgn.config;

// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ModelAttribute;

// @ControllerAdvice
// public class GlobalControllerAdvice {

//     @ModelAttribute
//     public void handleGlobalModelAttributes(Model model) {
//         boolean error = (Boolean) model.getAttribute("error");
//         if ((error != null) && error) {
//             model.addAttribute("message", "Username or password incorrect");
//         }
//     }

// }

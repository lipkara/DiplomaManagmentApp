package com.example.dpma.controller;

import com.example.dpma.model.Student;
import com.example.dpma.model.User;
import com.example.dpma.service.StudentService;
import com.example.dpma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @RequestMapping("/login")
    public String login() {
        return "auth/signin";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @RequestMapping("/save")
    public String registerUser(@ModelAttribute("user") User user, Model model) {

        if (userService.isUserPresent(user)) {
            model.addAttribute("successMessage", "User already registered!");
            return "auth/signin";
        }

        userService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");


        return "auth/signin";
    }

    @RequestMapping("/AboutUs")
    public String AboutUsPage() {
        return "auth/AboutUs";
    }


}
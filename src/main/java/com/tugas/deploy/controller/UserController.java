package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import com.tugas.deploy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final String USERNAME = "admin";
    private final String PASSWORD = "20230140161";

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Username admin, password NIM (Ganti dengan NIM kamu: 20230140161)
        if ("admin".equals(username) && "20230140161".equals(password)) {
            return "redirect:/home";
        }
        model.addAttribute("error", "Username atau Password Salah!");
        return "login";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        model.addAttribute("dataList", userService.getAllUsers());
        return "home";
    }

    // ================== CREATE ========================

    @GetMapping("/form")
    public String showFormPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/home";
    }


}
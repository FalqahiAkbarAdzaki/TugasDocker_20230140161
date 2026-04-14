package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    // Simpan data di memori (Temporary)
    private static List<User> daftarMahasiswa = new ArrayList<>();

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
        model.addAttribute("listMhs", daftarMahasiswa);
        return "home";
    }

    @GetMapping("/form")
    public String showFormPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        daftarMahasiswa.add(user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
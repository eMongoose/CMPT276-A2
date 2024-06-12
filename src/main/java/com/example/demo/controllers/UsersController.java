package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.Users;

@Controller
public class UsersController {
    @GetMapping("/users/view") // define an endpoint
    public String getAllUsers(Model model) {
        System.out.println("Get all users");
        List<Users> users = new ArrayList<>();
        users.add(new Users("emily", 50, 50, "red"));
        // end of database call

        model.addAttribute("us", users);
        return "users/showAll";
    }
}

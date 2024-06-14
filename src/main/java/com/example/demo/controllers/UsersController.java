package com.example.demo.controllers;

import java.util.List;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.User;
import com.example.demo.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UsersController {
    @Autowired
    private UserRepository userRepo;

    // view rectangles in data base
    @GetMapping("/users/view") // define an endpoint
    public String getAllUsers(Model model) {
        System.out.println("Get all users");
        List<User> users = userRepo.findAll();
        //users.add(new User("emily", 50, 50, "red"));
        // end of database call

        model.addAttribute("us", users);
        return "users/showAll";
    }
    // ================================================================================================

    // add rectangle to database
    @PostMapping("/users/add")
    // extract parameters from request
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        System.out.println("ADD user"); // console log, user has been added.

        // parsing data
        String name = newuser.get("name");
        int width = Integer.parseInt(newuser.get("width")); // Correctly parse int
        int height = Integer.parseInt(newuser.get("height")); // Correctly parse int
        String color = newuser.get("color"); // Directly use the String

        // create and save the user object
        userRepo.save(new User(name, width, height, color));
        response.setStatus(201); // created a new object
        return "redirect:/users/view";
    }

    // ================================================================================================
    // remove rectangle from database
    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        System.out.println("DELETE user"); // console log, user has been deleted.

        // delete rectangle
        userRepo.deleteById(id);
        return "redirect:/users/view";
    }
}

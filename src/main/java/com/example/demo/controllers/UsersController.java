package com.example.demo.controllers;

import java.util.List;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        // users.add(new User("emily", 50, 50, "red"));
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
        String name = newuser.getOrDefault("name", "No name");
        int width = parseIntOrDefault(newuser.get("width"), 250);
        int height = parseIntOrDefault(newuser.get("height"), 250);
        String color = newuser.getOrDefault("color", "#FFFFFF");

        // create and save the user object
        userRepo.save(new User(name, width, height, color));
        return "redirect:/users/view";
    }

    // check if the value exists
    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
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

    // ================================================================================================
    // edit rectangle
    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> userOptional = userRepo.findById(id);

        model.addAttribute("user", userOptional.get());
        return "users/editUser"; 
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("user") User userForm,
            RedirectAttributes redirectAttributes) {
        Optional<User> userOptional = userRepo.findById(id);

        User user = userOptional.get();
        user.setName(userForm.getName());
        user.setWidth(userForm.getWidth());
        user.setHeight(userForm.getHeight());
        user.setColor(userForm.getColor());

        userRepo.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
        return "redirect:/users/view";
    }
}

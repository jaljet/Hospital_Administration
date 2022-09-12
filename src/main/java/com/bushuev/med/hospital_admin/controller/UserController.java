package com.bushuev.med.hospital_admin.controller;

import com.bushuev.med.hospital_admin.entity.User;
import com.bushuev.med.hospital_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


//    @GetMapping("/main")
//    public String main() {
//        return "main";
//    }

//    @GetMapping("/")
//    public String home(){
//        return "home";
//    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/";
    }

    @GetMapping("/allUsers")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.findAll());

        return "allUsers";
    }

    @GetMapping("/createUser")
    public String createUserForm(User user) {
        return "createUser";
    }

    @PostMapping("/createUser")
    public String createUser(User user) {
        userService.addUser(user);

        return "redirect:/allUsers";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/allUsers";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUserById(userId);

        return "redirect:/allUsers";
    }

    @PostMapping("login")
    public String login(User user){


        return "main";
    }


}

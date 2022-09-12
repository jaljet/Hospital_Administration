package com.bushuev.med.hospital_admin.controller;

import com.bushuev.med.hospital_admin.entity.Role;
import com.bushuev.med.hospital_admin.entity.User;
import com.bushuev.med.hospital_admin.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if(userFromDB!= null){
            model.addAttribute("message", "User exists!");

            return "registration";
        }

        user.setActive(true);
        user.setRoles(Stream.of(Role.USER, Role.PATIENT)
                .collect(Collectors.toSet()));

        userRepository.save(user);

        return "redirect:/main";
    }


}

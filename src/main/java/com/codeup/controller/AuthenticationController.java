package com.codeup.controller;

import com.codeup.models.*;
import com.codeup.repositories.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by roxana on 6/29/17.
 */
@Controller
public class AuthenticationController {
    private PreferenceRepository preferenceRepository;

    @Autowired
    public AuthenticationController(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());

        //send preferences to the view
        Iterable<Preference> preferences = preferenceRepository.findAll();
        model.addAttribute("preferences", preferences);
        return "users/register";
    }
}

package com.codeup.controller;

import com.codeup.models.*;
import com.codeup.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by roxana on 6/29/17.
 */
@Controller
public class UsersController {
    private final UsersRepository usersRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Value("${users-img-path}")
    private String usersImgPath;

    @Autowired
    public UsersController(UsersRepository usersRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @PostMapping("/users/register")
    public String saveUser(@ModelAttribute User user, @RequestParam(name = "file") MultipartFile uploadedFile, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String filename = transferUploadedFile(uploadedFile, usersImgPath, model);

        user.setImgUrl(filename);
        usersRepository.save(user);

        // create a default role for each user ROLE_USER
        UserRole userRole = new UserRole(user.getId(), "ROLE_USER");
        rolesRepository.save(userRole);

        return "redirect:/login";
    }


    public static String transferUploadedFile(MultipartFile uploadedFile, String imgPath, Model model) {
        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(imgPath, filename).toString();
        File destinationFile = new File(filepath);

        try {
            uploadedFile.transferTo(destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }
        return filename;
    }
}

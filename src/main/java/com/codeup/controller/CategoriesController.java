package com.codeup.controller;

import com.codeup.models.Category;
import com.codeup.models.User;
import com.codeup.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by roxana on 6/28/17.
 */
@Controller
public class CategoriesController {
    private final CategoriesRepository categoriesRepository;

    @Value("${categories-img-path}")
    private String categoriesImgPath;

    @Autowired
    public CategoriesController(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @GetMapping("/home")
    public String viewHome(Model model) {
        //get default categories using user_id of admin
        List<Category> categories = categoriesRepository.findByUser_Id(1);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getId() != 0) {
            List<Category> customCategories = categoriesRepository.findByUser_Id(user.getId());
            categories.addAll(customCategories);
        }

        model.addAttribute("categories", categories);
        return "testingTables";

    }

    @GetMapping("/categories/create")
    public String createCategory(Model model) {
        model.addAttribute("categories", new Category());
        return "categories/create";
    }

    @PostMapping("/categories/create")
    public String saveCategory(@ModelAttribute Category category, @RequestParam(name = "file") MultipartFile uploadedFile, Model model) {
        String filename = UsersController.transferUploadedFile(uploadedFile, categoriesImgPath, model);
        category.setImgUrl(filename);
        categoriesRepository.save(category);
        return "redirect:/home";
    }

}

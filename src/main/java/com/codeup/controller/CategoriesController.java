package com.codeup.controller;

import com.codeup.models.Category;
import com.codeup.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

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
        Iterable<Category> categories = categoriesRepository.findAll();
        model.addAttribute("categories", categories);
        return "index";
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

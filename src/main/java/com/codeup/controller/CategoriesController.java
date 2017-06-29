package com.codeup.controller;

import com.codeup.models.Categories;
import com.codeup.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        Iterable<Categories> categories = categoriesRepository.findAll();
        model.addAttribute("categories", categories);
        return "testingTables";
    }

    @GetMapping("/categories/create")
    public String createCategory(Model model) {
        Iterable<Categories> categories = categoriesRepository.findAll();
        model.addAttribute("categories", categories);
        return "categories/create";
    }

    @PostMapping("/categories/create")
    public String saveCategory(Model model) {

        return "resdirect:/home";
    }

}

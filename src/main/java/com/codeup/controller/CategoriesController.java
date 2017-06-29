package com.codeup.controller;

import com.codeup.models.Categories;
import com.codeup.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by roxana on 6/28/17.
 */
@Controller
public class CategoriesController {
    private final CategoriesRepository categoriesRepository;

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

}

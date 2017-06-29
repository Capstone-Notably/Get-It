package com.codeup.controller;

import com.codeup.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}

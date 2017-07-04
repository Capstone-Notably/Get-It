package com.codeup.controller;

import com.codeup.models.*;
import com.codeup.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by roxana on 7/3/17.
 */
@Controller
public class GroceryListsController {
    private final GroceryListsRepository groceryListsRepository;

    @Autowired
    public GroceryListsController(GroceryListsRepository groceryListsRepository) {
        this.groceryListsRepository = groceryListsRepository;
    }

    @GetMapping("/lists")
    public String viewLists(Model model) {
        Iterable<GroceryList> glists = groceryListsRepository.findAll();
        model.addAttribute("lists", glists);
        return "lists/index";
    }
}

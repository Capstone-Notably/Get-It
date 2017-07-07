package com.codeup.controller;

import com.codeup.models.*;
import com.codeup.repositories.CategoriesRepository;
import com.codeup.repositories.RecipeItemsRepository;
import com.codeup.repositories.RecipesRepository;
import com.codeup.repositories.UserCategoryRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roxana on 7/7/17.
 */
@Controller
public class RecipesController {
    private final RecipesRepository recipesRepository;
    private final RecipeItemsRepository recipeItemsRepository;

    @Autowired
    public RecipesController(RecipesRepository recipesRepository, RecipeItemsRepository recipeItemsRepository) {
        this.recipesRepository = recipesRepository;
        this.recipeItemsRepository = recipeItemsRepository;
    }

//    public static List<Recipe> findAll(RecipesRepository recipesRepository, RecipeItemsRepository recipeItemsRepository) {
//        List<Recipe> recipes = new ArrayList<>();
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (!principal.equals("anonymousUser")) {
//            User user = (User) principal;
//            List<RecipeItem> recipeItems = recipeItemsRepository
//
//            for (UserRecipe userCategory : userCategories) {
//                Category category = categoriesRepository.findOne(userCategory.getCategory().getId());
//                recipes.add(category);
//            }
//        } else {
//            //get default categories using user_id admin
//            recipes = recipesRepository.findByUser_Id(1);
//        }
//        return recipes;
//    }
}

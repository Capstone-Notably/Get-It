package com.codeup.controller;

import com.codeup.models.*;
import com.codeup.repositories.*;
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
    private final UserRecipeRepository userRecipeRepository;
    private final ItemsRepository itemsRepository;

    @Autowired
    public RecipesController(RecipesRepository recipesRepository, RecipeItemsRepository recipeItemsRepository, UserRecipeRepository userRecipeRepository,ItemsRepository itemsRepository) {
        this.recipesRepository = recipesRepository;
        this.recipeItemsRepository = recipeItemsRepository;
        this.userRecipeRepository = userRecipeRepository;
        this.itemsRepository = itemsRepository;
    }

    public static List<Recipe> findAll(RecipesRepository recipesRepository, UserRecipeRepository userRecipeRepository) {
        List<Recipe> recipes = new ArrayList<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!principal.equals("anonymousUser")) {
            User user = (User) principal;
            List<UserRecipe> userRecipes = userRecipeRepository.findByUser_Id(user.getId());

            for (UserRecipe userRecipe : userRecipes) {
                Recipe recipe = recipesRepository.findOne(userRecipe.getRecipe().getId());
                recipes.add(recipe);
            }
        } else {
            //get default categories using user_id admin
            recipes = recipesRepository.findByUser_Id(1);
        }
        return recipes;
    }

    public static List<CustomItem> findAllItems(RecipesRepository recipesRepository, UserRecipeRepository userRecipeRepository, RecipeItemsRepository recipeItemsRepository, ItemsRepository itemsRepository) {
        List<Recipe> recipes = new ArrayList<>();
        List<RecipeItem> recipeItemsByRecipe_Id = new ArrayList<>();
        List<CustomItem> recipeItems =new ArrayList<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!principal.equals("anonymousUser")) {
            User user = (User) principal;
            List<UserRecipe> userRecipes = userRecipeRepository.findByUser_Id(user.getId());

            for (UserRecipe userRecipe : userRecipes) {
                Recipe recipe = recipesRepository.findOne(userRecipe.getRecipe().getId());
                recipes.add(recipe);
                List<RecipeItem> byRecipeId = recipeItemsRepository.findByRecipe_Id(recipe.getId());
                recipeItemsByRecipe_Id.addAll(byRecipeId);
                for (RecipeItem itembyRecipeId : byRecipeId) {
                    Item item = itemsRepository.findOne(itembyRecipeId.getItem().getId());
                    recipeItems.add(new CustomItem(item, recipe.getId()));
                }
            }
        } else {
            //get default categories using user_id admin
            recipes = recipesRepository.findByUser_Id(1);
            for (Recipe recipe : recipes) {
                List<RecipeItem> byRecipeId = recipeItemsRepository.findByRecipe_Id(recipe.getId());
                recipeItemsByRecipe_Id.addAll(byRecipeId);
                for (RecipeItem itembyRecipeId : byRecipeId) {
                    Item item = itemsRepository.findOne(itembyRecipeId.getItem().getId());
                    recipeItems.add(new CustomItem(item, recipe.getId()));
                }
            }
        }
        return recipeItems;
    }
}

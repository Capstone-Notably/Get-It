package com.codeup.controller;

import com.codeup.models.*;
import com.codeup.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Value("${recipes-img-path}")
    private String recipesImgPath;

    @Autowired
    public RecipesController(RecipesRepository recipesRepository, RecipeItemsRepository recipeItemsRepository, UserRecipeRepository userRecipeRepository,ItemsRepository itemsRepository) {
        this.recipesRepository = recipesRepository;
        this.recipeItemsRepository = recipeItemsRepository;
        this.userRecipeRepository = userRecipeRepository;
        this.itemsRepository = itemsRepository;
    }

    @PostMapping("/recipes/create")
    public String createRecipe(@ModelAttribute Recipe recipe, @RequestParam(name = "file") MultipartFile uploadedFile, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String filename = UsersController.transferUploadedFile(uploadedFile, recipesImgPath, model);

        if (filename.isEmpty()) {
            filename = "recipe_default.png";
        }
        recipe.setImgUrl(filename);
        recipe.setUser(user);
        Recipe savedRecipe = recipesRepository.save(recipe);

        // update users_recipes table
        userRecipeRepository.save(new UserRecipe(savedRecipe, user));

        // update recipe_items table
        // find items for temporal recipe and update recipe_id
        Recipe temporalRecipe = recipesRepository.findByUser_IdAndName(user.getId(), "temporal recipe");
        List<RecipeItem> recipeItems = recipeItemsRepository.findByRecipe_Id(temporalRecipe.getId());
        for (RecipeItem recipeItem : recipeItems) {
            recipeItem.setRecipe(savedRecipe);
            recipeItemsRepository.save(recipeItem);
        }

        return "redirect:/";
    }

    @PostMapping("/recipes/items")
    public void saveItems(@RequestBody CustomItem jsonString) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //find id of temporal recipe
        Recipe temporalRecipe = recipesRepository.findByUser_IdAndName(user.getId(), "temporal recipe");
        Item item = itemsRepository.findOne(jsonString.getId());
        RecipeItem recipeItem = new RecipeItem("", temporalRecipe, item);
        recipeItemsRepository.save(recipeItem);
    }

    @GetMapping("/recipe/delete")
    public String deleteRecipe(@RequestParam("recipe_id") long recipe_id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserRecipe userRecipe = userRecipeRepository.findByUser_IdAndRecipe_Id(user.getId(), recipe_id);
        userRecipeRepository.delete(userRecipe);
        Recipe recipe = recipesRepository.findOne(recipe_id);
        recipesRepository.delete(recipe);
        return "redirect:/";
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

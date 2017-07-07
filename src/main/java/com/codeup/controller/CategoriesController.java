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
 * Created by roxana on 6/28/17.
 */
@Controller
public class CategoriesController {
    private final CategoriesRepository categoriesRepository;
    private final UserCategoryRepository userCategoryRepository;
    private final RecipesRepository recipesRepository;
    private final RecipeItemsRepository recipeItemsRepository;
    private final UserRecipeRepository userRecipeRepository;

    @Value("${categories-img-path}")
    private String categoriesImgPath;

    @Autowired
    public CategoriesController(CategoriesRepository categoriesRepository, UserCategoryRepository userCategoryRepository, RecipesRepository recipesRepository, RecipeItemsRepository recipeItemsRepository, UserRecipeRepository userRecipeRepository) {
        this.categoriesRepository = categoriesRepository;
        this.userCategoryRepository = userCategoryRepository;
        this.recipesRepository = recipesRepository;
        this.recipeItemsRepository = recipeItemsRepository;
        this.userRecipeRepository = userRecipeRepository;
    }

    @GetMapping("/")
    public String viewHome(Model model) {
        List<Category> categories = findAll(categoriesRepository, userCategoryRepository);
        List<Recipe> recipes = RecipesController.findAll(recipesRepository, userRecipeRepository, recipeItemsRepository);
        model.addAttribute("categories", categories);
        model.addAttribute("recipes", recipes);
        model.addAttribute("newCategory", new Category());
        return "index";

    }

//    @GetMapping("/categories/create")
//    public String createCategory(Model model) {
//        model.addAttribute("categories", new Category());
//        return "redirect:/";
//    }

    @PostMapping("/categories/create")
    public String saveCategory(@ModelAttribute Category category, @RequestParam(name = "file") MultipartFile uploadedFile, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String filename = UsersController.transferUploadedFile(uploadedFile, categoriesImgPath, model);
        Preference preference = userCategoryRepository.findByUser_Id(user.getId()).get(0).getPreference();
        String strPreference = String.valueOf(preference.getId());

        if(filename.isEmpty()) {
            filename = "default_category.png";
        }

        // update categories table
        category.setImgUrl(filename);
        category.setPreferences(strPreference);
        category.setUser(user);
        categoriesRepository.save(category);

        // update users_categories table
        UserCategory userCategory = new UserCategory(category, user, preference);
        userCategoryRepository.save(userCategory);

        return "redirect:/home";
    }

    public static List<Category> findAll(CategoriesRepository categoriesRepository, UserCategoryRepository userCategoryRepository) {
        List<Category> categories = new ArrayList<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!principal.equals("anonymousUser")) {
            User user = (User) principal;
            List<UserCategory> userCategories = userCategoryRepository.findByUser_Id(user.getId());

            for (UserCategory userCategory : userCategories) {
                Category category = categoriesRepository.findOne(userCategory.getCategory().getId());
                categories.add(category);
            }
        } else {
            //get default categories using user_id of admin
            categories = categoriesRepository.findByUser_Id(1);
        }
        return categories;
    }

}

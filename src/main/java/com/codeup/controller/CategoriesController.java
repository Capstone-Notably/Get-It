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
    private final ItemsRepository itemsRepository;
    private final UserItemsRepository userItemsRepository;

    @Value("${categories-img-path}")
    private String categoriesImgPath;

    @Autowired
    public CategoriesController(CategoriesRepository categoriesRepository, UserCategoryRepository userCategoryRepository,
                                RecipesRepository recipesRepository, RecipeItemsRepository recipeItemsRepository,
                                UserRecipeRepository userRecipeRepository, ItemsRepository itemsRepository,
                                UserItemsRepository userItemsRepository) {
        this.categoriesRepository = categoriesRepository;
        this.userCategoryRepository = userCategoryRepository;
        this.recipesRepository = recipesRepository;
        this.recipeItemsRepository = recipeItemsRepository;
        this.userRecipeRepository = userRecipeRepository;
        this.itemsRepository = itemsRepository;
        this.userItemsRepository = userItemsRepository;
    }

    @GetMapping("/")
    public String viewHome(Model model) {
        List<Category> categories = findAll(categoriesRepository, userCategoryRepository);
        List<Recipe> recipes = RecipesController.findAll(recipesRepository, userRecipeRepository);
        List<CustomItem> recipeItems = RecipesController.findAllItems(recipesRepository, userRecipeRepository, recipeItemsRepository, itemsRepository);
        List<CustomItem> customItems = ItemsController.findByUser(itemsRepository, userItemsRepository);
        model.addAttribute("items", customItems);
        model.addAttribute("categories", categories);
        model.addAttribute("recipes", recipes);
        model.addAttribute("recipeItems", recipeItems);
        model.addAttribute("newCategory", new Category());
        model.addAttribute("newRecipe", new Recipe());
        return "index";

    }

    @PostMapping("/categories/create")
    public String saveCategory(@ModelAttribute Category category, @RequestParam(name = "name") String name,  @RequestParam(name = "file") MultipartFile uploadedFile, Model model) {

        if(!name.isEmpty()) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String filename = UsersController.transferUploadedFile(uploadedFile, categoriesImgPath, model);
            Preference preference = userCategoryRepository.findByUser_Id(user.getId()).get(0).getPreference();
            String strPreference = String.valueOf(preference.getId());

            if (filename.isEmpty()) {
                filename = "category_default.png";
            }

            // update categories table
            category.setName(name);
            category.setImgUrl(filename);
            category.setPreferences(strPreference);
            category.setUser(user);
            categoriesRepository.save(category);

            // update users_categories table
            UserCategory userCategory = new UserCategory(category, user, preference);
            userCategoryRepository.save(userCategory);
        }

        return "redirect:/";
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

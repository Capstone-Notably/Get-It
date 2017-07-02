package com.codeup.controller;

import com.codeup.models.Category;
import com.codeup.models.User;
import com.codeup.models.UserCategory;
import com.codeup.repositories.CategoriesRepository;
import com.codeup.repositories.UserCategoryRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roxana on 6/28/17.
 */
@Controller
public class CategoriesController {
    private final CategoriesRepository categoriesRepository;
    private final UserCategoryRepository userCategoryRepository;

    @Value("${categories-img-path}")
    private String categoriesImgPath;

    @Autowired
    public CategoriesController(CategoriesRepository categoriesRepository, UserCategoryRepository userCategoryRepository) {
        this.categoriesRepository = categoriesRepository;
        this.userCategoryRepository = userCategoryRepository;
    }

    @GetMapping("/home")
    public String viewHome(Model model) {
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

        model.addAttribute("categories", categories);
        return "testingTables";

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

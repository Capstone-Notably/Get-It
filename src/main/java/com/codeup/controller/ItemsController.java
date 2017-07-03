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
public class ItemsController {
    private final ItemsRepository itemsRepository;
    private final UserItemsRepository userItemsRepository;
    private final CategoriesRepository categoriesRepository;
    private final UserCategoryRepository userCategoryRepository;

    @Value("${items-img-path}")
    private String itemsImgPath;

    @Autowired
    public ItemsController(ItemsRepository itemsRepository, UserItemsRepository userItemsRepository, CategoriesRepository categoriesRepository, UserCategoryRepository userCategoryRepository) {
        this.itemsRepository = itemsRepository;
        this.userItemsRepository = userItemsRepository;
        this.categoriesRepository = categoriesRepository;
        this.userCategoryRepository = userCategoryRepository;
    }

    @GetMapping("/items")
    public String viewItems(@RequestParam("category_id") long category_id, Model model) {
        List<CustomItem> customItems = getItems(itemsRepository, userItemsRepository, category_id);
        model.addAttribute("items", customItems);
        return "items/index";
    }

    @GetMapping("/items/create")
    public String createItem(Model model) {
        List<Category> categories = CategoriesController.getCategories(categoriesRepository, userCategoryRepository);
        model.addAttribute("categories", categories);
        model.addAttribute("item", new CustomItem());
        return "items/create";
    }

    @PostMapping("/items/create")
    public String saveItem(@ModelAttribute CustomItem item, @RequestParam(name = "file") MultipartFile uploadedFile, @RequestParam(name = "categoryName") String categoryName, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String filename = UsersController.transferUploadedFile(uploadedFile, itemsImgPath, model);
        Category category = categoriesRepository.findByName(categoryName);
        String preference = String.valueOf(userCategoryRepository.findByUser_Id(user.getId()).get(0).getPreference().getId());

        if(filename.isEmpty()) {
            filename = "default_item.png";
        }
        Item defaultItem = new Item(item.getName(), filename, preference, category, user);
        UserItem userItem = new UserItem(item.getPrice(), item.getQuantity(), item.getBarcode(), item.isFavorite(), user, defaultItem);
        itemsRepository.save(defaultItem);
        userItemsRepository.save(userItem);
        return "redirect:/home";
    }

    public static List<CustomItem> getItems(ItemsRepository itemsRepository, UserItemsRepository userItemsRepository, long category_id) {
        List<CustomItem> customItems = new ArrayList<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principal.equals("anonymousUser")) {
            User user = (User) principal;
            List<UserItem> userItems = userItemsRepository.findByUser_Id(user.getId());

            for (UserItem userItem : userItems) {
                Item item = itemsRepository.findOne(userItem.getItem().getId());
                if(item.getCategory().getId() == category_id) {
                    CustomItem customItem = new CustomItem(item.getName(), item.getImgUrl(), userItem.getPrice(), userItem.getQuantity(), userItem.getBarcode(), userItem.isFavorite());
                    customItems.add(customItem);
                }
            }

        } else {
            // get the default items using user_id=1 -> admin user
            List<Item> items = itemsRepository.findByUser_IdAndCategory_Id(1, category_id);
            for (Item item : items) {
                customItems.add(new CustomItem(item.getName(), item.getImgUrl()));
            }
        }
        return customItems;
    }

}

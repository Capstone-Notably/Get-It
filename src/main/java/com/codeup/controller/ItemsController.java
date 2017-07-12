package com.codeup.controller;

import com.codeup.models.*;
import com.codeup.repositories.*;
import com.google.gson.Gson;
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
    private final ListItemsRepository listItemsRepository;
    private final GroceryListsRepository groceryListsRepository;

    @Value("${items-img-path}")
    private String itemsImgPath;

    @Autowired
    public ItemsController(ItemsRepository itemsRepository, UserItemsRepository userItemsRepository, CategoriesRepository categoriesRepository,
                           UserCategoryRepository userCategoryRepository, ListItemsRepository listItemsRepository, GroceryListsRepository groceryListsRepository) {
        this.itemsRepository = itemsRepository;
        this.userItemsRepository = userItemsRepository;
        this.categoriesRepository = categoriesRepository;
        this.userCategoryRepository = userCategoryRepository;
        this.listItemsRepository = listItemsRepository;
        this.groceryListsRepository = groceryListsRepository;
    }

    @GetMapping("/items/create")
    public String createItem(Model model) {
        List<Category> categories = CategoriesController.findAll(categoriesRepository, userCategoryRepository);
        model.addAttribute("categories", categories);
        model.addAttribute("item", new CustomItem());
        return "items/create";
    }

    @PostMapping("/items/create")
    public String createItem(@ModelAttribute CustomItem item, @RequestParam(name = "file") MultipartFile uploadedFile, @RequestParam(name = "categoryName") String categoryName, Model model) {
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
        return "redirect:/";
    }

    @GetMapping("/items.json")
    public @ResponseBody String findItems() {
        List<CustomItem> items = findByUser(itemsRepository, userItemsRepository);
        String json = new Gson().toJson(items);
        return json;
    }

    @PostMapping("/items/addToList")
    public String addToList(@RequestParam("item_id") long item_id, @RequestParam("glist_id") long glist_id) {
        GroceryList glist = groceryListsRepository.findOne(glist_id);
        Item item = itemsRepository.findOne(item_id);
        listItemsRepository.save(new ListItem(glist, item));
        return "redirect:/";
    }

    public static List<CustomItem> findByCategory(ItemsRepository itemsRepository, UserItemsRepository userItemsRepository, long category_id) {
        List<CustomItem> customItems = new ArrayList<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principal.equals("anonymousUser")) {
            User user = (User) principal;
            List<UserItem> userItems = userItemsRepository.findByUser_Id(user.getId());

            for (UserItem userItem : userItems) {
                Item item = itemsRepository.findOne(userItem.getItem().getId());
                if(item.getCategory().getId() == category_id) {
                    CustomItem customItem = new CustomItem(item, userItem);
                    customItems.add(customItem);
                }
            }

        } else {
            // get the default items using user_id=1 -> admin user
            List<Item> items = itemsRepository.findByUser_IdAndCategory_Id(1, category_id);
            for (Item item : items) {
                customItems.add(new CustomItem(item.getId(), item.getName(), item.getImgUrl()));
            }
        }
        return customItems;
    }

    public static List<CustomItem> findByUser(ItemsRepository itemsRepository, UserItemsRepository userItemsRepository) {
        List<CustomItem> customItems = new ArrayList<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principal.equals("anonymousUser")) {
            User user = (User) principal;
            List<UserItem> userItems = userItemsRepository.findByUser_Id(user.getId());

            for (UserItem userItem : userItems) {
                Item item = itemsRepository.findOne(userItem.getItem().getId());
                CustomItem customItem = new CustomItem(item, userItem);
                customItems.add(customItem);
            }
        }else {
            // get the default items using user_id=1 -> admin user
            List<Item> items = (List<Item>) itemsRepository.findAll();
            for (Item item : items) {
                customItems.add(new CustomItem(item));
            }
        }
        return customItems;
    }

}

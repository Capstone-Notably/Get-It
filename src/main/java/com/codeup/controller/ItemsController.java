package com.codeup.controller;

import com.codeup.models.*;
import com.codeup.repositories.UserItemsRepository;
import com.codeup.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${items-img-path}")
    private String itemsImgPath;

    @Autowired
    public ItemsController(ItemsRepository itemsRepository, UserItemsRepository userItemsRepository) {
        this.itemsRepository = itemsRepository;
        this.userItemsRepository = userItemsRepository;
    }

    @GetMapping("/items")
    public String viewItems(@RequestParam("category_id") long category_id, Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principal.equals("anonymousUser")) {
            User user = (User) principal;
            List<CustomItem> customItems = new ArrayList<>();
            List<UserItem> userItems = userItemsRepository.findByUser_Id(user.getId());

            for (UserItem userItem : userItems) {
                Item item = itemsRepository.findOne(userItem.getItem().getId());
                CustomItem customItem = new CustomItem(item.getName(), item.getImgUrl(), userItem.getPrice(), userItem.getQuantity(), userItem.getBarcode(), userItem.isFavorite());
                customItems.add(customItem);
            }

            model.addAttribute("items", customItems);

        } else {
            // get the default items using user_id=1 -> admin user
            List<Item> items = itemsRepository.findByUser_IdAndCategory_Id(1, category_id);
            model.addAttribute("items", items);
        }

        return "items/index";
    }

    @GetMapping("/items/create")
    public String createItem(Model model) {
        model.addAttribute("items", new Item());
        return "items/create";
    }

    @PostMapping("/items/create")
    public String saveItem(@ModelAttribute Item item, @RequestParam(name = "file") MultipartFile uploadedFile, Model model) {
        String filename = UsersController.transferUploadedFile(uploadedFile, itemsImgPath, model);
        item.setImgUrl(filename);
        itemsRepository.save(item);
        return "redirect:/home";
    }

}

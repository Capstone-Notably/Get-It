package com.codeup.controller;

import com.codeup.models.*;
import com.codeup.repositories.CustomItemsRepository;
import com.codeup.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class ItemsController {
    private final ItemsRepository itemsRepository;
    private final CustomItemsRepository customItemsRepository;

    @Value("${items-img-path}")
    private String itemsImgPath;

    @Autowired
    public ItemsController(ItemsRepository itemsRepository, CustomItemsRepository customItemsRepository) {
        this.itemsRepository = itemsRepository;
        this.customItemsRepository = customItemsRepository;
    }

    @GetMapping("/items")
    public String viewItems(@RequestParam("category_id") long category_id, Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principal.equals("anonymousUser")) {
            User user = (User) principal;
            List<UserItem> userItems = new ArrayList<>();
//            items = itemsRepository.findByUser_IdAndCategory_Id(user.getId(), category_id);

            List<CustomItem> customItems = customItemsRepository.findByUser_Id(user.getId());

            for (CustomItem customItem : customItems) {
                Item item = itemsRepository.findOne(customItem.getItem().getId());
                UserItem userItem = new UserItem(item.getName(), item.getImgUrl(), customItem.getPrice(), customItem.getQuantity(), customItem.getBarcode(), customItem.isFavorite());
                userItems.add(userItem);
            }

            model.addAttribute("items", userItems);

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

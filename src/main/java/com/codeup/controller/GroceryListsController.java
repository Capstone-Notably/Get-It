package com.codeup.controller;

import com.codeup.models.*;
import com.codeup.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roxana on 7/3/17.
 */
@Controller
public class GroceryListsController {
    private final GroceryListsRepository groceryListsRepository;
    private final ItemsRepository itemsRepository;
    private final UserItemsRepository userItemsRepository;
    private final ListItemsRepository listItemsRepository;

    @Autowired
    public GroceryListsController(GroceryListsRepository groceryListsRepository, ItemsRepository itemsRepository,
                                  UserItemsRepository userItemsRepository, ListItemsRepository listItemsRepository) {
        this.groceryListsRepository = groceryListsRepository;
        this.itemsRepository = itemsRepository;
        this.userItemsRepository = userItemsRepository;
        this.listItemsRepository = listItemsRepository;
    }

    @GetMapping("/lists")
    public String viewLists(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CustomItem> customItems = new ArrayList<>();
        List<GroceryList> glists = groceryListsRepository.findAllByUser_Id(user.getId());

        // get Ids for all the items
        for (GroceryList glist : glists) {
            List<ListItem> listItems = new ArrayList<>();
            listItems.addAll(listItemsRepository.findByGlist_Id(glist.getId()));
            for (ListItem listItem : listItems) {
                long itemId = listItem.getItem().getId();
                Item item = itemsRepository.findOne(itemId);
                UserItem userItem = userItemsRepository.findByUser_IdAndItem_Id(user.getId(), itemId);
                customItems.add(new CustomItem(item.getId(), item.getName(), item.getImgUrl(), userItem.getPrice(), userItem.getQuantity(), userItem.getBarcode(), userItem.isFavorite(), glist.getId()));
            }
        }

        model.addAttribute("items", customItems);
        model.addAttribute("lists", glists);
        return "lists/index";
    }

    @PostMapping("/lists/items")
    public void saveItem(@RequestBody CustomItem jsonString) {
        long listId = jsonString.getListId();
        GroceryList glist = groceryListsRepository.findOne(listId);
        String name = jsonString.getName();
        Item item = itemsRepository.findByName(name);
        ListItem listItem = new ListItem(glist, item);
        listItemsRepository.save(listItem);
    }

}

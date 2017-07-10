package com.codeup.controller;

import com.codeup.models.*;
import com.codeup.repositories.*;
import com.codeup.svcs.TwilioSvc;
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
    private final UserGListRepository userGListRepository;
    private final UsersRepository usersRepository;
    private final TwilioSvc twilioSvc;

    @Autowired
    public GroceryListsController(GroceryListsRepository groceryListsRepository, ItemsRepository itemsRepository,
                                  UserItemsRepository userItemsRepository, ListItemsRepository listItemsRepository,
                                  UserGListRepository userGListRepository, TwilioSvc twilioSvc, UsersRepository usersRepository) {
        this.groceryListsRepository = groceryListsRepository;
        this.itemsRepository = itemsRepository;
        this.userItemsRepository = userItemsRepository;
        this.listItemsRepository = listItemsRepository;
        this.userGListRepository = userGListRepository;
        this.twilioSvc = twilioSvc;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/lists")
    public String viewLists(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CustomItem> customItems = new ArrayList<>();
        List<UserGList> userGLists = userGListRepository.findByUser_Id(user.getId());
        List<GroceryList> glists = new ArrayList<>();
        for (UserGList userGList : userGLists) {
            GroceryList glist = groceryListsRepository.findOne(userGList.getGlist().getId());
            glists.add(glist);
        }

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

    @PostMapping("/list/share")
    public String shareList(@RequestParam("phone") String phone, @RequestParam("listId") long listId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GroceryList glist = groceryListsRepository.findOne(listId);

        //share list
        User user1 = usersRepository.findByPhone(phone);
        userGListRepository.save(new UserGList(glist, user1));

        // send text to share list
//        String message = user.getUsername() + "wants to share \"" + glist.getName() + "\" with you";
//        twilioSvc.sendMessage(phone,"+18304200837",message);
        return "redirect:/lists";
    }

    @PostMapping("/list/create")
    public String saveList(@RequestParam("name") String name) {
        if(!name.isEmpty()) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            GroceryList glist = groceryListsRepository.save(new GroceryList(name));
            userGListRepository.save(new UserGList(glist, user));
        }
        return "redirect:/lists";
    }

}

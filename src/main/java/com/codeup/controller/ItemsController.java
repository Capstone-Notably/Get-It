package com.codeup.controller;

import com.codeup.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by roxana on 6/28/17.
 */
@Controller
public class ItemsController {
    private final ItemsRepository itemsRepository;


    @Autowired
    public ItemsController(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }
}

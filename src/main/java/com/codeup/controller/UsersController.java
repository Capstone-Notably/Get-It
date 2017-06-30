package com.codeup.controller;

import com.codeup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by roxana on 6/29/17.
 */
@Controller
public class UsersController {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}

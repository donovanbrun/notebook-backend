package com.donovan.notebook.controller;

import com.donovan.notebook.model.User;
import com.donovan.notebook.service.NoteService;
import com.donovan.notebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    // TODO : add id session when user get connected and use it to manage security

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, NoteService noteService) {
        this.userService = userService;
    }

    @PostMapping()
    public void addUser(@RequestBody User user) {

        if (!this.userService.exist(user.getUsername())) {
            this.userService.addUser(user);
            throw new ResponseStatusException(HttpStatus.CREATED, "User created with username : " + user.getUsername());
        }
        else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ID already taken");
        }
    }
}
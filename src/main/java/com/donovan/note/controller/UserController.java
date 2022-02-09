package com.donovan.note.controller;

import com.donovan.note.model.User;
import com.donovan.note.service.NoteService;
import com.donovan.note.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/user")
//@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    // TODO : add id session when user get connected and use it to manage security

    private final UserService userService;
    private final NoteService noteService;

    @Autowired
    public UserController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping()
    public void addUser(@RequestBody User user) {

        if (!this.userService.exist(user.getUsername())) {
            this.userService.addUser(user);

            try {
                this.noteService.createDir(user.getUsername(), ".", "root");
            } catch (Exception e) {
                e.printStackTrace();
            }

            throw new ResponseStatusException(HttpStatus.CREATED, "User created with username : " + user.getUsername());
        }
        else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ID already taken");
        }
    }
}
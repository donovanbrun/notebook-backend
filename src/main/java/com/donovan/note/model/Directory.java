package com.donovan.note.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class Directory extends AbstractDirectory {

    @Id
    private String username;

    public Directory(String username) {
        super("root");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
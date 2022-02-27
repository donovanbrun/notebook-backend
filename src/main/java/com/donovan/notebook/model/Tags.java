package com.donovan.notebook.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("Tags")
public class Tags {

    @Id
    private String username;
    private ArrayList<String> tags;
}

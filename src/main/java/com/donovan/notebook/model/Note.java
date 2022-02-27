package com.donovan.notebook.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Notes")
public class Note {

    // TODO add tags

    @Id
    private Long id;
    private String username;
    private String name;
    private String content;

    public Note(String username, String name, String content) {
        this.id = (long) (Math.random() * Long.MAX_VALUE);
        this.username = username;
        this.name = name;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

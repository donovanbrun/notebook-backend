package com.donovan.notebook.controller;

import com.donovan.notebook.model.Note;
import com.donovan.notebook.model.ReceivedNote;
import com.donovan.notebook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "api/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // TODO Remove this route after dev
    @GetMapping()
    public List<Note> getNotes() {
        return noteService.getNotes();
    }

    @GetMapping("{username}")
    public List<Note> getNote(@PathVariable String username) {
        return noteService.getNotesByUsername(username);
    }

    // TODO change username to something more security like cookies
    @PostMapping()
    public void createNote(@RequestBody ReceivedNote body) {
        try {
            this.noteService.createNote(body.getUsername(), body.getName(), body.getContent());
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.CREATED);
    }

    @PutMapping()
    public void saveNote(@RequestBody ReceivedNote body) {
        this.noteService.saveNote(body.getUsername(), body.getName(), body.getContent());
    }
}

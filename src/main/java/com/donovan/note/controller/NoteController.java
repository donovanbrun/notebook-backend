package com.donovan.note.controller;

import com.donovan.note.model.Directory;
import com.donovan.note.model.ReceivedNote;
import com.donovan.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/note")
//@CrossOrigin(origins = "http://localhost:8080")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping()
    public List<Directory> getNotes() {
        return noteService.getNotes();
    }

    @GetMapping("{username}")
    public Optional<Directory> getNote(@PathVariable String username) {
        return noteService.getNote(username);
    }

    // TODO change username to something more security, and maybe use requestBody to transform it into
    @PostMapping("createdir/{username}/{path}/{dirname}") // path like "root.dir1.dir2.ect"
    public void createDir(@PathVariable String username, @PathVariable String path, @PathVariable String dirname) {

        try {
            this.noteService.createDir(username, path, dirname);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.CREATED);
    }

    @PostMapping("createnote")
    public void createNote(@RequestBody ReceivedNote body) {
        try {
            this.noteService.createNote(body.getUsername(), body.getPath(), body.getName(), body.getContent());
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.CREATED);
    }
}

package com.donovan.notebook.service;

import com.donovan.notebook.model.Note;
import com.donovan.notebook.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class NoteService { // TODO implement overload security with note's character limit (like 4-5k)

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /*public boolean exist(String username) {
        return this.noteRepository.existsById(username);
    }*/

    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public List<Note> getNotesByUsername(String username) {
        // TODO use the commented code
        //return this.noteRepository.findNotesByUsername(username);
        return this.noteRepository.findAll().stream().filter(new Predicate<Note>() {
            @Override
            public boolean test(Note note) {
                return note.getUsername().equals(username);
            }
        }).collect(Collectors.toList());
    }

    public void createNote(String username, String name, String content) throws Exception {
        // TODO check if note already exist, and check params conformity
        // TODO use exception
        this.noteRepository.save(new Note(username, name, content));
    }

    public void saveNote(String username, String name, String content) {
        // TODO use mongo aggregation

        for (Note n : this.noteRepository.findAll()) {
            if (n.getUsername().equals(username) && n.getName().equals(name)) {
                n.setContent(content);
                this.noteRepository.save(n);
                break;
            }
        }
    }
}

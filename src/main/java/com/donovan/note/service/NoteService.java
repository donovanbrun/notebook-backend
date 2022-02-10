package com.donovan.note.service;

import com.donovan.note.model.Note;
import com.donovan.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return this.noteRepository.findNotesByUsername(username);
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

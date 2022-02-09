package com.donovan.note.service;

import com.donovan.note.model.Directory;
import com.donovan.note.model.Note;
import com.donovan.note.model.SubDirectory;
import com.donovan.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public boolean exist(String username) {
        return this.noteRepository.existsById(username);
    }

    public void createDir(String username, String path, String dirname) throws Exception {

        //TODO check sub directories and try to do something
        Optional<Directory> optRoot = noteRepository.findById(username);

        if (optRoot.isPresent()) {
            Directory root = optRoot.get();
            // TEMPORARY !!!!! don't compute path
            root.addDirectory(new SubDirectory(dirname));
            this.noteRepository.save(root);
        }
        else {
            Directory dir = new Directory(username);
            noteRepository.insert(dir);
        }
    }

    public List<Directory> getNotes() {
        return noteRepository.findAll();
    }

    public Optional<Directory> getNote(String username) {
        return this.noteRepository.findById(username);
    }

    public void createNote(String username, String path, String name, String content) throws Exception {

        Optional<Directory> optRoot = noteRepository.findById(username);

        if (optRoot.isPresent()) {
            Directory root = optRoot.get();
            ///////////////////////////////////////////////////////////////////////////////
            // TEMPORARY !!!!! don't compute path
            Note note = null;
            for (Note n : root.getNotes()) {
                if (n.getName().equals(name)) {
                    note = n;
                    break;
                }
            }

            if (note != null) {
                note.setContent(content);
            }
            else {
                root.addNote(new Note(username, name, content));
            }

            this.noteRepository.save(root);
            ///////////////////////////////////////////////////////////////////////////////
        }
        else {
            Directory dir = new Directory(username);
            noteRepository.insert(dir);
        }
    }
}

package com.donovan.note.model;

import java.util.ArrayList;
import java.util.function.Function;

public abstract class AbstractDirectory {

    protected String name;
    protected ArrayList<SubDirectory> directories;
    protected ArrayList<Note> notes;

    public AbstractDirectory(String name) {
        this.name = name;
        this.directories = new ArrayList<SubDirectory>();
        this.notes = new ArrayList<Note>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<SubDirectory> getDirectories() {
        return directories;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
/*
    public boolean explore(String path) {
        String[] dirNames = path.split(".");

        SubDirectory nextDir = null;

        for (SubDirectory d : directories) {
            if (d.getName().equals(dirNames[0])) {
                nextDir = d;
                break;
            }
        }

        StringBuilder newPath = new StringBuilder();

        for (int i = 1; i < dirNames.length; i++) {
            newPath.append(dirNames[i]);
            if (i < dirNames.length-1) newPath.append('.');
        }

        if (nextDir != null) {
            return nextDir.explore(newPath.toString());
        }
        return true
    }
*/
    public void addNote(Note note) throws Exception {
        if (note != null) {
            for (Note n : this.notes) {
                if (note.getName().equals(n.getName())) {
                    throw new Exception("There is already a note with this name");
                }
            }
            this.notes.add(note);
        }
    }

    public void addDirectory(SubDirectory subDirectory) throws Exception {
        if (subDirectory != null) {
            for (SubDirectory d : this.directories) {
                if (subDirectory.getName().equals(d.getName())) {
                    throw new Exception("There is already a directory with this name");
                }
            }
            this.directories.add(subDirectory);
        }
    }
}

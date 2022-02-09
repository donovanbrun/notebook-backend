package com.donovan.note.repository;

import com.donovan.note.model.Directory;
import com.donovan.note.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Directory, String> {
}

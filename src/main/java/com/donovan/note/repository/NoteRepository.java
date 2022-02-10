package com.donovan.note.repository;

import com.donovan.note.model.Note;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, Long> {

    // TODO make it works
    @Aggregation(pipeline = "{$match: { username: {username:?0} }}")
    List<Note> findNotesByUsername(String username);
}

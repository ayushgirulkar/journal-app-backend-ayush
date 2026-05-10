package com.example.demo.reposiratory;

import com.example.demo.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryReposiratory extends MongoRepository<JournalEntry, ObjectId>
{

}

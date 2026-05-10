package com.example.demo.service;

import com.example.demo.entity.JournalEntry;
import com.example.demo.reposiratory.JournalEntryReposiratory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

   @Autowired
    private JournalEntryReposiratory journalEntryReposiratory;


   public void saveEntry(JournalEntry journalEntry)
   {
       journalEntryReposiratory.save(journalEntry);
   }
   public Optional<JournalEntry> findById(ObjectId id)
   {
       return journalEntryReposiratory.findById(id);
   }
   public void deleteById(ObjectId id)
   {
       journalEntryReposiratory.deleteById(id);
   }
    public List<JournalEntry> getAll() {

        return journalEntryReposiratory.findAll();
    }



}

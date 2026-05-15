package com.example.demo.service;

import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;
import com.example.demo.reposiratory.JournalEntryReposiratory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

   @Autowired
    private JournalEntryReposiratory journalEntryReposiratory;
    @Autowired
    private UserService userService;

    @Transactional
   public void saveEntry(JournalEntry journalEntry, String userName)
   {
       try {


           User user = userService.findByUserName(userName);
           JournalEntry saved = journalEntryReposiratory.save(journalEntry);

           user.getJournalEntries().add(saved);
           userService.saveUser(user);
       }
       catch (Exception e)
       {
           System.out.print(e);
           throw  new RuntimeException("error aa gya bhai svae entry mai");
       }
   }
    public void saveEntry(JournalEntry journalEntry)
    {
        journalEntryReposiratory.save(journalEntry);
    }

   public Optional<JournalEntry> findById(ObjectId id)
   {
       return journalEntryReposiratory.findById(id);
   }
   @Transactional
   public boolean deleteById(ObjectId id, String userName)
   {
       boolean removed=false;
       try {

           User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
           if (removed) {
               userService.saveUser(user);
               journalEntryReposiratory.deleteById(id);
           }
       } catch (Exception e) {
           throw new RuntimeException("error while deleting entry" +e);
       }
       return removed;

   }
    public List<JournalEntry> getAll() {

        return journalEntryReposiratory.findAll();
    }

    public List<JournalEntry>findByUserName(String userName)
    {
        return journalEntryReposiratory.findAll();
    }



}

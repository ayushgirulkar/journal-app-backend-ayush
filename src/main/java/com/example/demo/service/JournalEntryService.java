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
           userService.saveEntry(user);
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
   public void deleteById(ObjectId id, String userName)
   {
       User user=userService.findByUserName(userName);
       user.getJournalEntries().removeIf(x->x.getId().equals(id));
       userService.saveEntry(user);
       journalEntryReposiratory.deleteById(id);
   }
    public List<JournalEntry> getAll() {

        return journalEntryReposiratory.findAll();
    }



}

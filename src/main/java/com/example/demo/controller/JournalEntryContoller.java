package com.example.demo.controller;


import com.example.demo.entity.JournalEntry;
import com.example.demo.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryContoller {


    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll()
    {
        return journalEntryService.getAll();
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry)
    {
        try
            {

                journalEntryService.saveEntry(myEntry);
                myEntry.setDate(LocalDateTime.now());
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            catch (Exception e)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId)
    {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent())
        {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.FOUND);
        }
        else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("id/{myId}")
    public ResponseEntity<JournalEntry> deleteJournalEntryById(@PathVariable ObjectId myId)
    {
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("id/{id}")
    public  JournalEntry updateJpurnalById(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry)
    {
        JournalEntry oldEntry  = journalEntryService.findById(id).orElse(null);
        if (oldEntry!=null)
        {
            oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle(): oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent(): oldEntry.getContent());
        }
        journalEntryService.saveEntry(oldEntry);

        oldEntry.setDate(LocalDateTime.now());
        return  oldEntry;
    }



}

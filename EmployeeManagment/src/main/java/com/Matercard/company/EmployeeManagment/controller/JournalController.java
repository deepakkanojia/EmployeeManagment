package com.Matercard.company.EmployeeManagment.controller;

import com.Matercard.company.EmployeeManagment.Service.JornalEntryServices;
import com.Matercard.company.EmployeeManagment.entity.JournalEntity;
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
public class JournalController {

    @Autowired
    private JornalEntryServices jornalEntryServices;


    @GetMapping
    public List<JournalEntity> getAll() {
        return jornalEntryServices.getAllEntry();
    }

    @PostMapping
    public ResponseEntity<JournalEntity> createEntity(@RequestBody JournalEntity journalEntity) {
        try {
            journalEntity.setDate(LocalDateTime.now());
            jornalEntryServices.saveEntry(journalEntity);
            return new ResponseEntity<>(journalEntity,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "id/{myId}")
    public ResponseEntity<JournalEntity> getJournalById(@PathVariable ObjectId myId) {
        Optional<JournalEntity> journalEntity =jornalEntryServices.getJornalBYId(myId);
        if(journalEntity.isPresent()){
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "id/{myId}")
    public ResponseEntity<JournalEntity> deleteJournalById(@PathVariable ObjectId myId) {
        jornalEntryServices.deleteJornalBYId(myId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntity> putJournalEntity(@PathVariable ObjectId id, @RequestBody JournalEntity journalEntity) {
        JournalEntity old = jornalEntryServices.getJornalBYId(id).orElse(null);
        if (old != null) {
            old.setTitle(journalEntity.getTitle() != null && !journalEntity.getTitle().equals("") ? journalEntity.getTitle() : old.getTitle());
            old.setContent(journalEntity.getContent() != null && !journalEntity.getContent().equals("") ? journalEntity.getContent() : old.getContent());
            jornalEntryServices.saveEntry(journalEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
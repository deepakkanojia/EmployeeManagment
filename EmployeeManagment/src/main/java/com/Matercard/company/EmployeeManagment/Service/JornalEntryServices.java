package com.Matercard.company.EmployeeManagment.Service;

import com.Matercard.company.EmployeeManagment.entity.JournalEntity;
import com.Matercard.company.EmployeeManagment.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class JornalEntryServices {

    @Autowired

    private JournalEntryRepo journalEntryRepo;


    public void saveEntry(JournalEntity journalEntity) {
        journalEntryRepo.save(journalEntity);
    }

    public List<JournalEntity> getAllEntry() {
       return journalEntryRepo.findAll();
    }


    public Optional<JournalEntity> getJornalBYId(ObjectId myId) {
        return journalEntryRepo.findById(myId);
    }

    public boolean deleteJornalBYId(ObjectId myId) {
        journalEntryRepo.deleteById(myId);
        return true;
    }
}

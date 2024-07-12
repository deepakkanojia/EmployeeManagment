package com.Matercard.company.EmployeeManagment.repository;

import com.Matercard.company.EmployeeManagment.entity.JournalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntity,Object> {
}

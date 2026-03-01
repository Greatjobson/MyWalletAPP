package com.finapp.finapp.Repository;

import com.finapp.finapp.Model.Entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction,String> {

    boolean existsByTagId(String tagId);
    List<Transaction> findByDateBetween(LocalDate from,LocalDate to);
}

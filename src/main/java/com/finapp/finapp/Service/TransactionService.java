package com.finapp.finapp.Service;

import com.finapp.finapp.Model.Entity.Transaction;
import com.finapp.finapp.Model.DTO.TransactionCreateDTO;
import com.finapp.finapp.Repository.TransactionRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final MongoTemplate mongoTemplate;


    public TransactionService(TransactionRepository transactionRepository, MongoTemplate mongoTemplate) {
        this.transactionRepository = transactionRepository;
        this.mongoTemplate = mongoTemplate;
    }



    public List<Transaction> getTransaction(String tagId,String assetId, LocalDate from, LocalDate to){
        Query query = new Query();

        if (tagId != null)
            query.addCriteria(Criteria.where("tagId").is(tagId));
        if (assetId != null)
            query.addCriteria(Criteria.where("assetId").is(assetId));
        if (from != null && to != null)
            query.addCriteria(Criteria.where("date").gte(from).lte(to));

        return mongoTemplate.find(query, Transaction.class);

    }


    public Transaction createTransaction(TransactionCreateDTO dto) {
        Transaction entity = toTransaction(new Transaction(),dto);
        return transactionRepository.insert(entity);
    }


    public Transaction updateTransaction(String id,TransactionCreateDTO dto) {
        Transaction ExactEntity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //херня чтобы из optional -> Transaction

        Transaction entity = toTransaction(ExactEntity,dto);

        return transactionRepository.save(entity);
    }


    public void deleteTransaction(String id){
        if (!transactionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getTransactionBetweenDates(LocalDate from,LocalDate to){
        return transactionRepository.findByDateBetween(from,to);
    }

    // Ручной маппинг
    // createdAt не трогаем, его Spring сам подставит через @CreatedDate
    private Transaction toTransaction(Transaction entity,TransactionCreateDTO dto){

        entity.setTagId(dto.getTagId());
        entity.setAssetId(dto.getAssetId());
        entity.setAmount(dto.getAmount());
        entity.setNote(dto.getNote());

        return entity;
    }
}

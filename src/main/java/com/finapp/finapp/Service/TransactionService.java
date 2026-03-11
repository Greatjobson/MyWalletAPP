package com.finapp.finapp.Service;

import com.finapp.finapp.Model.Entity.Transaction;
import com.finapp.finapp.Model.DTO.TransactionCreateDTO;
import com.finapp.finapp.Model.TransactionType;
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
    TagService tagService;


    public TransactionService(TransactionRepository transactionRepository, MongoTemplate mongoTemplate,TagService tagService) {
        this.transactionRepository = transactionRepository;
        this.mongoTemplate = mongoTemplate;
        this.tagService = tagService;
    }



    public List<Transaction> getTransaction(String tagId,String assetId, LocalDate from, LocalDate to, TransactionType type){
        Query query = new Query();

        if (tagId != null)
            query.addCriteria(Criteria.where("tagId").is(tagId));
        if (assetId != null)
            query.addCriteria(Criteria.where("assetId").is(assetId));
        if (type != null)
            query.addCriteria(Criteria.where("type").is(type));
        if (from != null && to != null)
            query.addCriteria(Criteria.where("date").gte(from).lte(to));

        return mongoTemplate.find(query, Transaction.class);

    }


    public Transaction createTransaction(TransactionCreateDTO dto) {
        if(!tagService.existById(dto.getTagId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found v1.0");
        }
        Transaction entity = toTransaction(new Transaction(),dto);

        return transactionRepository.insert(entity);
    }


    public Transaction updateTransaction(String id,TransactionCreateDTO dto) {
        Transaction ExactEntity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //херня чтобы из optional -> Transaction

        Transaction entity = toTransaction(ExactEntity,dto);

        return transactionRepository.save(entity);
    }

    /**
     * вообщем такой синтаксис должен быть во всех вбросах исключений глобальный обработчик его примети выбраосит
     * @param id
     * @return
     */
    public Transaction findById(String id){
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Transaction with id " + id + " not found"));
    }

    public void deleteTransaction(String id){
        if (!transactionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        transactionRepository.deleteById(id);
    }



    // Ручной маппинг
    // createdAt не трогаем, его Spring сам подставит через @CreatedDate
    private Transaction toTransaction(Transaction entity,TransactionCreateDTO dto){

        entity.setTagId(dto.getTagId());
        entity.setAssetId(dto.getAssetId());
        entity.setAmount(dto.getAmount());
        entity.setNote(dto.getNote());

        TransactionType type = tagService.findById(dto.getTagId()) //берем тэг
                .getTransactionType();//бере тип транзакции

        entity.setType(type);

        return entity;
    }
}

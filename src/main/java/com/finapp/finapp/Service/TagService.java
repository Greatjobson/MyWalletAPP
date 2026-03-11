package com.finapp.finapp.Service;

import com.finapp.finapp.Model.DTO.TagCreatDTO;
import com.finapp.finapp.Model.Entity.Tag;
import com.finapp.finapp.Repository.TagRepository;
import com.finapp.finapp.Repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TagService {
    private final TransactionRepository transactionRepository;
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository, TransactionRepository transactionRepository){
        this.tagRepository = tagRepository;
        this.transactionRepository = transactionRepository;
    }


//todo find by id logic

    public Tag findById(String id){
        return tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"tag not found"));
    }


    public boolean existById(String id){
        return tagRepository.existsById(id);
    }

    public List<Tag> findTag(){

        return tagRepository.findAll();
    }


    public Tag createTag(TagCreatDTO dto) {
        Tag entity = toTag(new Tag(),dto);
        return tagRepository.insert(entity);
    }


    public Tag updateTag(String id,TagCreatDTO dto) {
        Tag ExactEntity = tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Tag entity = toTag(ExactEntity,dto);//dto -> tag

        return tagRepository.save(entity);
    }


    public void deleteTag(String id){
        if (!tagRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        if (transactionRepository.existsByTagId(id)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CONFLICT");
        }
        tagRepository.deleteById(id);
    }


    // Ручной маппинг
    private Tag toTag(Tag entity,TagCreatDTO dto){

        entity.setUserId(dto.getUserId());
        entity.setName(dto.getName());
        entity.setTransactionType(dto.getTransactionType());

        return entity;
    }
}

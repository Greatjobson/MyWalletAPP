package com.finapp.finapp.Controller;

import com.finapp.finapp.Model.DTO.TagCreatDTO;
import com.finapp.finapp.Model.Entity.Tag;
import com.finapp.finapp.Service.TagService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("{id}")
    public Tag findById(@PathVariable String id){
        return tagService.findById(id);
    }

    @PostMapping ("")
    public ResponseEntity<String> createTag(@Valid @RequestBody TagCreatDTO tag){
        tagService.createTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tag successfully created");
    }

    @GetMapping("")
    public ResponseEntity<List<Tag>> findTag(){

        List<Tag> tag = tagService.findTag();
        return ResponseEntity.status(HttpStatus.OK).body(tag);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateTag(@PathVariable String id,@Valid @RequestBody TagCreatDTO dto){
        tagService.updateTag(id,dto);
        return ResponseEntity.status(HttpStatus.OK).body("Tag successfully updated");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTag(@PathVariable String id){
        tagService.deleteTag(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tag successfully deleted");
    }

}

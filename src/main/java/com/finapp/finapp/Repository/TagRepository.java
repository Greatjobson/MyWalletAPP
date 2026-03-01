package com.finapp.finapp.Repository;

import com.finapp.finapp.Model.Entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends MongoRepository<Tag,String> {
}

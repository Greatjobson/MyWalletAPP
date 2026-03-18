package com.finapp.finapp.Repository;

import com.finapp.finapp.Model.Entity.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends MongoRepository<Asset,String> {
}

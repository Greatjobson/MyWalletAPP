package com.finapp.finapp.Service;

import com.finapp.finapp.Model.Entity.Asset;
import com.finapp.finapp.Repository.AssetRepository;
import com.finapp.finapp.Repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AssetService {
    private final AssetRepository assetRepository;
    private final TransactionRepository transactionRepository;

    public AssetService(AssetRepository assetRepository,TransactionRepository transactionRepository){
        this.assetRepository = assetRepository;
        this.transactionRepository = transactionRepository;
    }

    public Asset createAsset(Asset asset){
        return assetRepository.insert(asset);
    }

    // todo фильтрацию ассетов добавить при необходимости
    public List<Asset> getAllAsset(){
        return assetRepository.findAll();
    }
    public Asset getAssetById(String id){
        return assetRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"asset not")
        );
    }

    public Asset update(String id,Asset assetUPDATED){
        Asset asset = assetRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"no asset with id: " + id));

        asset.setAmount(assetUPDATED.getAmount());
        asset.setNote(assetUPDATED.getNote());
        asset.setCurrency(assetUPDATED.getCurrency());

        return assetRepository.save(asset);
    }

    public void delete(String id){
        if(!assetRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no asset with id: " + id);
        }
        if (transactionRepository.existsByAssetId(id)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Asset with id: " + id + "already used in transactions");
        }

        assetRepository.deleteById(id);
    }
}

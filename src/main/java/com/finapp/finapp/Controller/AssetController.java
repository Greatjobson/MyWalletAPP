package com.finapp.finapp.Controller;

import com.finapp.finapp.Model.Entity.Asset;
import com.finapp.finapp.Service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {
    AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }


    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(){
        List<Asset> assets = assetService.getAllAsset();

        BigDecimal sumOfAllAssets = BigDecimal.ZERO;
        for (Asset asset : assets){
            sumOfAllAssets = sumOfAllAssets.add(asset.getBalance());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sumOfAllAssets);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Asset>> getAllAsset(){
        List<Asset> assets =  assetService.getAllAsset();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(assets);
    }

    @PostMapping("/")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset){
        Asset createdAsset = assetService.createAsset(asset);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdAsset);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> findAsset(@PathVariable String id){
        Asset asset = assetService.getAssetById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(asset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable String id,@RequestBody Asset updatedAsset){
        Asset asset = assetService.update(id,updatedAsset);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(asset);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(String id) {
        assetService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("deleted");
    }

}

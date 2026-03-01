package com.finapp.finapp.Controller;

import com.finapp.finapp.Model.Entity.Transaction;
import com.finapp.finapp.Model.DTO.TransactionCreateDTO;
import com.finapp.finapp.Service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/filter")
    public List<Transaction> filter(
            @RequestParam(required = false) String tagId,
            @RequestParam(required = false) String assetId,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to
    ) {
        return transactionService.getTransaction(tagId, assetId, from, to);
    }

    @GetMapping("/")
    public List<Transaction> findTransactionBetweenDates(@RequestParam LocalDate from, @RequestParam LocalDate to){
        return transactionService.getTransactionBetweenDates(from,to);
    }

    @PostMapping("/post")
    public ResponseEntity<String> insertTransaction(@Valid @RequestBody TransactionCreateDTO dto){

        Transaction created = transactionService.createTransaction(dto);

        //todo
        // возварашать тело created
        return ResponseEntity.ok("Данные верны, сохраняем!");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateTransaction(@PathVariable String id,@Valid @RequestBody TransactionCreateDTO dto){
        transactionService.updateTransaction(id,dto);
        return ResponseEntity.ok("updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok("deleted");
    }
}

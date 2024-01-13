package com.ensa.client.web;

import com.ensa.client.DTO.SendMoneyRequest;
import com.ensa.client.DTO.WalletBalanceRequest;
import com.ensa.client.entity.Transaction;
import com.ensa.client.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
@CrossOrigin
public class ClientController {
    private final WalletService walletService;

    @PostMapping("/balance")
    public ResponseEntity<Double> getWalletBalance(@RequestBody WalletBalanceRequest walletBalanceRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.set("Accept","application/json");
        return new ResponseEntity<>(walletService.getWalletBalance(walletBalanceRequest),headers,HttpStatus.OK);
    }

    @PutMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity sendMoney(@RequestBody SendMoneyRequest sendMoneyRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.set("Accept","application/json");
        walletService.sendMoney(sendMoneyRequest);
        return new ResponseEntity<>(headers,HttpStatus.OK);
    }

    @PostMapping("/extourne")
    public ResponseEntity<List<Transaction>> findExtourneTransactions(@RequestBody WalletBalanceRequest walletBalanceRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.set("Accept","application/json");
        return new ResponseEntity<>(walletService.findExtourneTransactions(walletBalanceRequest),headers,HttpStatus.OK);
    }

    @PostMapping("/servis")
    public ResponseEntity<List<Transaction>> findServisTransactions(@RequestBody WalletBalanceRequest walletBalanceRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.set("Accept","application/json");
        return new ResponseEntity<>(walletService.findServisTransactions(walletBalanceRequest),headers,HttpStatus.OK);
    }

    @PutMapping("/annuler")
    public ResponseEntity extourne(@RequestBody Transaction transaction){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.set("Accept","application/json");
        walletService.extourne(transaction);
        return new ResponseEntity<>(headers,HttpStatus.OK);
    }


}

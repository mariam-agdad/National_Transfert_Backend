package com.ensa.agent.web;

import com.ensa.agent.DTO.AddAmountToWalletRequest;
import com.ensa.agent.DTO.AddClientRequest;
import com.ensa.agent.DTO.ClientResponse;
import com.ensa.agent.entity.Client;
import com.ensa.agent.entity.Transaction;
import com.ensa.agent.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agent")
@RequiredArgsConstructor
@CrossOrigin
public class AgentController {
    private final AgentService agentService;

    @PostMapping("/addClient")
    public ResponseEntity addClient(@RequestBody AddClientRequest addClientRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.set("Accept","application/json");
        agentService.addClient(addClientRequest);
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @PutMapping("/updateBalance")
    public ResponseEntity addAmountToWallet(@RequestBody AddAmountToWalletRequest addAmountToWalletRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.set("Accept","application/json");
        agentService.addAmountToWallet(addAmountToWalletRequest);
        return new ResponseEntity<>(headers,HttpStatus.OK);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> findAllTransactions(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.set("Accept","application/json");
        return new ResponseEntity<>(agentService.findAllTransactions(),headers,HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientResponse>> findAllClients(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.set("Accept","application/json");
        return new ResponseEntity<>(agentService.findAllClients(),headers,HttpStatus.OK);
    }

}

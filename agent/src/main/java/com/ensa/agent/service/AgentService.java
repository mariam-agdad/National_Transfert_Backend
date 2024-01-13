package com.ensa.agent.service;

import com.ensa.agent.DTO.AddAmountToWalletRequest;
import com.ensa.agent.DTO.AddClientRequest;
import com.ensa.agent.DTO.ClientResponse;
import com.ensa.agent.entity.Client;
import com.ensa.agent.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgentService {
    void addClient(AddClientRequest addClientRequest);
    void addAmountToWallet(AddAmountToWalletRequest addAmountToWalletRequest);
    List<Transaction> findAllTransactions();
    List<ClientResponse> findAllClients();
}

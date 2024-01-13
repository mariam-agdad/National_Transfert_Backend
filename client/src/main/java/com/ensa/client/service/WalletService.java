package com.ensa.client.service;


import com.ensa.client.DTO.SendMoneyRequest;
import com.ensa.client.DTO.WalletBalanceRequest;
import com.ensa.client.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WalletService {
    Double getWalletBalance(WalletBalanceRequest walletBalanceRequest);
    void sendMoney(SendMoneyRequest sendMoneyRequest);

    List<Transaction> findExtourneTransactions(WalletBalanceRequest walletBalanceRequest);

    List<Transaction> findServisTransactions(WalletBalanceRequest walletBalanceRequest);

    void extourne(Transaction transaction);

}

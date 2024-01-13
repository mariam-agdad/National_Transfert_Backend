package com.ensa.client.service;

import com.ensa.client.DTO.SendMoneyRequest;
import com.ensa.client.DTO.WalletBalanceRequest;
import com.ensa.client.entity.Client;
import com.ensa.client.entity.Status;
import com.ensa.client.entity.Transaction;
import com.ensa.client.repo.ClientRepository;
import com.ensa.client.repo.TransactionRepository;
import com.ensa.client.repo.WalletRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class WalletServiceImpl implements WalletService
{

    private final ClientRepository clientRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Double getWalletBalance(WalletBalanceRequest walletBalanceRequest) {
        Optional<Client> client = clientRepository.findClientByEmailAndPassword(walletBalanceRequest.getEmail(),walletBalanceRequest.getPassword());
        if (client.isPresent()) {
            return client.get().getWallet().getBalance();
        }
        else {
            throw new RuntimeException("Client not Found");
        }
    }

    @Override
    public void sendMoney(SendMoneyRequest sendMoneyRequest) {
        Optional<Client> sender = clientRepository.findClientByEmailAndPassword(sendMoneyRequest.getSenderEmail(),sendMoneyRequest.getSenderPassword());
        Optional<Client> receiver = clientRepository.findClientByEmail(sendMoneyRequest.getReceiverEmail());
        if (sender.isPresent()) {
            if(sender.get().getWallet().getBalance() < sendMoneyRequest.getAmount()){
                throw new RuntimeException("Balance is lower than the transaction amount");
            }
            else{
                if(receiver.isPresent()){
                    sender.get().getWallet().setBalance(sender.get().getWallet().getBalance() - sendMoneyRequest.getAmount());
                    receiver.get().getWallet().setBalance(receiver.get().getWallet().getBalance() + sendMoneyRequest.getAmount());
                    clientRepository.save(sender.get());
                    clientRepository.save(receiver.get());
                    Transaction transaction = Transaction.builder()
                            .amount(sendMoneyRequest.getAmount())
                            .receiverEmail(sendMoneyRequest.getReceiverEmail())
                            .senderEmail(sendMoneyRequest.getSenderEmail())
                            .status(Status.SERVIS)
                            .build();
                    transactionRepository.save(transaction);
                }
                else {
                    throw new RuntimeException("Receiver not Found");
                }
            }

        }
        else {
            throw new RuntimeException("Sender not Found");
        }

    }

    @Override
    public List<Transaction> findExtourneTransactions(WalletBalanceRequest walletBalanceRequest) {
        Optional<Client> client = clientRepository.findClientByEmailAndPassword(walletBalanceRequest.getEmail(),walletBalanceRequest.getPassword());
        if (client.isPresent()) {
            return transactionRepository.findTransactionByStatus(Status.EXTOURNE);
        }
        else {
            throw new RuntimeException("Client not Found");
        }

    }

    @Override
    public List<Transaction> findServisTransactions(WalletBalanceRequest walletBalanceRequest) {
        Optional<Client> client = clientRepository.findClientByEmailAndPassword(walletBalanceRequest.getEmail(),walletBalanceRequest.getPassword());
        if (client.isPresent()) {
            return transactionRepository.findTransactionByStatus(Status.SERVIS);
        }
        else {
            throw new RuntimeException("Client not Found");
        }
    }

    @Override
    public void extourne(Transaction transaction) {
        transaction.setStatus(Status.EXTOURNE);
        transactionRepository.save(transaction);
    }
}

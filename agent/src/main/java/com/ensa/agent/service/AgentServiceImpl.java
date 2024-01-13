package com.ensa.agent.service;

import com.ensa.agent.DTO.AddAmountToWalletRequest;
import com.ensa.agent.DTO.AddClientRequest;
import com.ensa.agent.DTO.ClientResponse;
import com.ensa.agent.entity.Client;
import com.ensa.agent.entity.Transaction;
import com.ensa.agent.entity.Wallet;
import com.ensa.agent.otp.OtpClient;
import com.ensa.agent.otp.SendEmailRequest;
import com.ensa.agent.repo.ClientRepository;
import com.ensa.agent.repo.TransactionRepository;
import com.ensa.agent.repo.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class AgentServiceImpl implements AgentService {

    private final  ClientRepository clientRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final OtpClient otpClient;
    @Override
    public void addClient(AddClientRequest addClientRequest) {
        Client client = Client.builder()
                .cin(addClientRequest.getCIN())
                .email(addClientRequest.getEmail())
                .firstName(addClientRequest.getFirstName())
                .lastName(addClientRequest.getLastName())
                .address(addClientRequest.getAddress())
                .password("0000")
                .build();
        Wallet wallet = Wallet.builder()
                .balance(0D)
                .build();
        client.setWallet(wallet);
        clientRepository.save(client);
        wallet.setClient(client);
        walletRepository.save(wallet);
       otpClient.sendEmail(SendEmailRequest.builder()
        .toEmail(addClientRequest.getEmail())
                       .subject("OTP")
                       .body("0000")
               .build());
    }

    @Override
    public void addAmountToWallet(AddAmountToWalletRequest addAmountToWalletRequest) {
        Optional<Wallet> wallet = walletRepository.findByClientCin(addAmountToWalletRequest.getCin());
        if (wallet.isPresent()) {
            wallet.get().setBalance(wallet.get().getBalance() + addAmountToWalletRequest.getAmount());
            walletRepository.save(wallet.get());
        }
        else {
            throw new RuntimeException("Wallet not Found");
        }
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<ClientResponse> findAllClients() {
        return clientRepository.findAll().stream()
                .map(client -> ClientResponse.builder().firstName(client.getFirstName()).lastName(client.getLastName()).balance(client.getWallet().getBalance()).walletId(client.getWallet().getId()).build()).collect(Collectors.toList());
    }
}

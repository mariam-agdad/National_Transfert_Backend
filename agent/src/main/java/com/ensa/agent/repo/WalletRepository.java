package com.ensa.agent.repo;

import com.ensa.agent.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findByClientId(String clientId);
    Optional<Wallet> findByClientCin(String clientCin);
}

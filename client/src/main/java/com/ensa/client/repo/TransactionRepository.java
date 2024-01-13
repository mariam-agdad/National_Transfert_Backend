package com.ensa.client.repo;

import com.ensa.client.entity.Status;
import com.ensa.client.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findTransactionByStatus(Status status);

}

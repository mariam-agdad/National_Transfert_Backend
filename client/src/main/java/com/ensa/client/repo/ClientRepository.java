package com.ensa.client.repo;

import com.ensa.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findClientByEmail(String email);
    Optional<Client> findClientByEmailAndPassword(String email, String password);
}

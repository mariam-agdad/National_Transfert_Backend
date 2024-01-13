package com.ensa.agent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String senderEmail;
    private String receiverEmail;
    private Double amount;
    @CreatedDate
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
}

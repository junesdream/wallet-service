package com.rainbowdev.expert.walletservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long walletId;
    private String type; // "DEPOSIT" oder "WITHDRAW"
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public Transaction(Long walletId, String type, BigDecimal amount) {
        this.walletId = walletId;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }
}
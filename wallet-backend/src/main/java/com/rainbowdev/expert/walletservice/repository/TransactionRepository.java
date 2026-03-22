package com.rainbowdev.expert.walletservice.repository;

import com.rainbowdev.expert.walletservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Spring versteht das "OrderByTimestampDesc" automatisch!
    List<Transaction> findByWalletIdOrderByTimestampDesc(Long walletId);
}
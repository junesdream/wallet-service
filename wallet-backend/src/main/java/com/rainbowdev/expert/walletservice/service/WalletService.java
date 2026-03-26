package com.rainbowdev.expert.walletservice.service;

import com.rainbowdev.expert.walletservice.model.Transaction;
import com.rainbowdev.expert.walletservice.model.Wallet;
import com.rainbowdev.expert.walletservice.repository.TransactionRepository;
import com.rainbowdev.expert.walletservice.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet createWallet(String owner, BigDecimal initialBalance) {
        Wallet wallet = new Wallet();
        wallet.setOwnerName(owner);
        wallet.setBalance(initialBalance);
        wallet.setCurrency("EUR");
        return walletRepository.save(wallet);
    }

    public Wallet deposit(Long id, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet nicht gefunden!"));

        wallet.setBalance(wallet.getBalance().add(amount));
        return walletRepository.save(wallet);
    }

    public Wallet withdraw(Long id, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet nicht gefunden!"));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Nicht genug Guthaben! Du hast nur: " + wallet.getBalance());
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        transactionRepository.save(new Transaction(id, "WITHDRAW", amount));
        return walletRepository.save(wallet);
    }

    public List<Transaction> getHistory(Long walletId) {
        return transactionRepository.findByWalletIdOrderByTimestampDesc(walletId);
    }
}
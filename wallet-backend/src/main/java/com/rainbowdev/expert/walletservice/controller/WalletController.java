package com.rainbowdev.expert.walletservice.controller;

import com.rainbowdev.expert.walletservice.model.Transaction;
import com.rainbowdev.expert.walletservice.model.Wallet;
import com.rainbowdev.expert.walletservice.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "https://wallet-service-rosy.vercel.app"})
@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    // GET http://localhost:8080/api/wallets
    @GetMapping
    public List<Wallet> getAll() {
        return walletService.getAllWallets();
    }

    // GET http://localhost:8080/api/wallets/1/history
    @GetMapping("/{id}/history")
    public List<Transaction> getHistory(@PathVariable Long id) {
        return walletService.getHistory(id);
    }

    // POST http://localhost:8080/api/wallets?owner=June&balance=100
    @PostMapping
    public Wallet create(@RequestParam String owner, @RequestParam BigDecimal balance) {
        return walletService.createWallet(owner, balance);
    }

    // PUT http://localhost:8080/api/wallets/1/deposit?amount=50.50
    @PutMapping("/{id}/deposit")
    public Wallet deposit(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return walletService.deposit(id, amount);
    }

    // PUT http://localhost:8080/api/wallets/1/withdraw?amount=100
    @PutMapping("/{id}/withdraw")
    public Wallet withdraw(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return walletService.withdraw(id, amount);
    }
}
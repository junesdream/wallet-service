package com.rainbowdev.expert.walletservice;

import com.rainbowdev.expert.walletservice.model.Wallet;
import com.rainbowdev.expert.walletservice.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final WalletRepository walletRepository;

    @Override
    public void run(String... args) {
        if (walletRepository.count() == 0) {
            Wallet demoWallet = new Wallet();
            demoWallet.setOwnerName("Expert-Demo-User");
            demoWallet.setBalance(new BigDecimal("1000.00"));
            demoWallet.setCurrency("EUR");
            walletRepository.save(demoWallet);
            System.out.println(">> Demo-Daten wurden geladen: Wallet ID 1 erstellt.");
        }
    }
}
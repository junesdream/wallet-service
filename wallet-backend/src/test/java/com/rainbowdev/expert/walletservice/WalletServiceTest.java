package com.rainbowdev.expert.walletservice.service;

import com.rainbowdev.expert.walletservice.model.Wallet;
import com.rainbowdev.expert.walletservice.repository.TransactionRepository;
import com.rainbowdev.expert.walletservice.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private WalletService walletService;

    private Wallet testWallet;

    @BeforeEach
    void setUp() {
        testWallet = new Wallet();
        testWallet.setId(1L);
        testWallet.setBalance(new BigDecimal("500.00"));
    }

    @Test
    void withdraw_ShouldDecreaseBalance_WhenFundsAreSufficient() {
        // GIVEN
        when(walletRepository.findById(1L)).thenReturn(Optional.of(testWallet));
        when(walletRepository.save(any(Wallet.class))).thenReturn(testWallet);

        // WHEN
        Wallet result = walletService.withdraw(1L, new BigDecimal("100.00"));

        // THEN
        assertEquals(new BigDecimal("400.00"), result.getBalance());
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    void withdraw_ShouldThrowException_WhenFundsAreInsufficient() {
        // GIVEN
        when(walletRepository.findById(1L)).thenReturn(Optional.of(testWallet));

        // WHEN & THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            walletService.withdraw(1L, new BigDecimal("1000.00"));
        });

        assertTrue(exception.getMessage().contains("Nicht genug Guthaben"));
        verify(walletRepository, never()).save(any());
    }
}
package com.grofandris.api.Bank.services;

import com.grofandris.api.Bank.models.Account;
import com.grofandris.api.Bank.repositories.AccountRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Data
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Double getBalance(Long id) {
        return accountRepository.findById(id).get().getBalance();
    }

    public Double withdrawFromBalance(Long id, Double amount) {
        Account account = accountRepository.findById(id).get();
        if (account.getBalance() < amount) {
            throw new RuntimeException("Account haven't got enough money for this transaction");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        System.out.println("Transaction was successful");
        return accountRepository.findById(id).get().getBalance();
    }

    public Double addToBalance(Long id, Double amount) {
        Account account = accountRepository.findById(id).get();
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return accountRepository.findById(id).get().getBalance();
    }
}

package com.grofandris.api.Bank.services;

import com.grofandris.api.Bank.models.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@NoArgsConstructor
public class AccountHandler {

    @Autowired
    private AccountService accountService;

    public AccountHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    public Account addAccount(Account account) {
        return accountService.saveAccount(account);
    }

    public Double withdraw(Long id, Double amount) {
        return accountService.withdrawFromBalance(id, amount);
    }

    public Double deposit(Long id, Double amount) {
        return accountService.addToBalance(id, amount);
    }

    public Double getBalance(Long id) {
        return accountService.getBalance(id);
    }

    public List<Double> transfer(Long sender_id , Long receiver_id, Double amount){
        accountService.withdrawFromBalance(sender_id,amount);
        accountService.addToBalance(receiver_id,amount);
        List<Double> response = new ArrayList<>();
        response.add(accountService.getAccountRepository().findById(sender_id).get().getBalance());
        response.add(accountService.getAccountRepository().findById(receiver_id).get().getBalance());
        return response;
    }
}

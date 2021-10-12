package com.grofandris.api.Bank.controllers;

import com.grofandris.api.Bank.models.Account;
import com.grofandris.api.Bank.services.AccountHandler;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bank/account")
public class AccountController {

    private AccountHandler handler;

    public AccountController(AccountHandler handler) {
        this.handler = handler;
    }

    @PostMapping("/save")
    public Account save(@Valid @RequestBody Account account) {
        return handler.addAccount(account);
    }

    @PostMapping("/withdraw/{id}/{amount}")
    public Double withdraw(@PathVariable("id") Long id,
                           @PathVariable("amount") Double amount) {
        return handler.withdraw(id, amount);
    }

    @PostMapping("/deposit/{id}/{amount}")
    public Double deposit(@PathVariable("id") Long id,
                          @PathVariable("amount") Double amount) {
        return handler.deposit(id, amount);
    }

    @GetMapping("getbalance/{id}")
    public Double getBalance(@PathVariable Long id) {
        return handler.getBalance(id);
    }

    @PostMapping("transfer/{sender}/{receiver}/{amount}")
    public List<Double> transfer(@PathVariable("sender") Long sender_id, @PathVariable("receiver") Long receiver_id, @PathVariable("amount") Double amount) {
        return handler.transfer(sender_id, receiver_id, amount);
    }
}

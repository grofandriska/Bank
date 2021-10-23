package com.grofandris.api.Bank.controllers;

import com.grofandris.api.Bank.models.Account;
import com.grofandris.api.Bank.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bank/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/save")
    public Account save(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @GetMapping("/withdraw/{id}/amount/{amount}")
    public Double withdraw(@PathVariable("id") Long id,
                           @PathVariable("amount") Double amount) {
        return accountService.withdrawFromBalance(id, amount);
    }

    @GetMapping("/deposit/{id}/{amount}")
    public Double deposit(@PathVariable("id") Long id,
                          @PathVariable("amount") Double amount) {
        return accountService.addToBalance(id, amount);
    }

    @GetMapping("/balance/{id}")
    public Double getBalance(@PathVariable Long id) {
        return accountService.getBalance(id);
    }

    @GetMapping("/transfer/{sender_id}/{receiver_id}/{amount}")
    public List<Double> transfer(@PathVariable("sender_id") Long sender_id,
                                 @PathVariable("receiver_id") Long receiver_id,
                                 @PathVariable Double amount) {
        return accountService.transferMoney(sender_id, receiver_id, amount);
    }
}

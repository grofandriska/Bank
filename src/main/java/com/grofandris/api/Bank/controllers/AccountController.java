package com.grofandris.api.Bank.controllers;

import com.grofandris.api.Bank.models.Account;
import com.grofandris.api.Bank.services.AccountService;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/withdraw/{id}/amount/{amount}")
    public void withdraw(@PathVariable("id") Long id,
                           @PathVariable("amount") Double amount) {
       accountService.withdrawFromBalance(id,amount);
    }

    @PostMapping("/deposit/{id}/{amount}")
    public Double deposit(@PathVariable("id") Long id,
                          @PathVariable("amount") Double amount) {
        return accountService.addToBalance(id, amount);
    }

    @GetMapping("getbalance/{id}")
    public Double getBalance(@PathVariable Long id) {
        return accountService.getBalance(id);
    }

}

package com.grofandris.api.Bank.services;

import com.grofandris.api.Bank.models.Account;
import com.grofandris.api.Bank.repositories.AccountRepository;
import lombok.Data;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Data
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    public Double getBalance(Long id) {
        Account account = accountRepository.findById(id).get();
        return account.getBalance();
    }
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.NESTED)
    public Double withdrawFromBalance(Long id, Double amount) {
        Account account = accountRepository.findById(id).get();
        if (account.getBalance() < amount) {
            throw new RuntimeException("Account haven't got enough money for this transaction");
        }
        account.setBalance(account.getBalance() - amount);
        Account update = accountRepository.save(account);
        System.out.println("Transaction was successful");
        return update.getBalance();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.NESTED)
    public Double addToBalance(Long id, Double amount) {
        Account account = accountRepository.findById(id).get();
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return accountRepository.findById(id).get().getBalance();
    }
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.NESTED)
    public List<Double> transferMoney(Long sender_id, Long receiver_id, Double amount) {
        Account sender = accountRepository.findById(sender_id).get();
        Account receiver = accountRepository.findById(receiver_id).get();
        if (sender.getBalance() < amount) {
            throw new RuntimeException("Account haven't got enough money for this transaction");
        }
        sender.setBalance(sender.getBalance() - amount);
        Account senderUpdate = accountRepository.save(sender);
        receiver.setBalance(receiver.getBalance() + amount);
        Account receiverUpdate = accountRepository.save(receiver);
        return List.of(senderUpdate.getBalance(),receiverUpdate.getBalance());
    }
}

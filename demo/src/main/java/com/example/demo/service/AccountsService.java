package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Accounts;
import com.example.demo.repository.AccountsRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountsService {
    private AccountsRepository accountRepo;

    public AccountsService(AccountsRepository accountRepo){
        this.accountRepo = accountRepo;
    }
    @Transactional
    public Accounts postNewUser(Accounts account){
        if(account.getUsername().isBlank() || account.getPassword().isBlank())
        {
            return null;
        }
        if(accountRepo.findByUsername(account.getUsername()).isPresent())
        {
            return null;
        }
        return accountRepo.save(account);
    }
}

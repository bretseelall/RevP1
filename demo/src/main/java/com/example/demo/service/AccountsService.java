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

    public Accounts postUserLogin(Accounts account){
        
        if(accountRepo.findByUsername(account.getUsername()).isPresent())
        {
            Accounts accountInDB = accountRepo.findByUsername(account.getUsername()).get();

            if((account.getUsername().equals(accountInDB.getUsername())) && account.getPassword().equals(accountInDB.getPassword())){
                return accountInDB;
            }
            else
                return null;
        }
        else
            return null;

        
    }
}

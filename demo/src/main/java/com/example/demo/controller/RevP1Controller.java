package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Accounts;
import com.example.demo.service.AccountsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class RevP1Controller {
    private AccountsService accountsService;

    public RevP1Controller(AccountsService accountsService){
        this.accountsService = accountsService;
    }

    @PostMapping("/register")
    public ResponseEntity<Accounts> postNewUser(@RequestBody Accounts accounts) {
        Accounts addedAccount = accountsService.postNewUser(accounts);

        if(addedAccount != null)
            return ResponseEntity.status(200).body(accounts);
        else
            return ResponseEntity.status(409).body(accounts);
    }
    
}

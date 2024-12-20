package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Accounts;
import com.example.demo.entity.Ticket;
import com.example.demo.entity.Ticket.Status;
import com.example.demo.service.AccountsService;
import com.example.demo.service.TicketService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class RevP1Controller {
    private AccountsService accountsService;
    private TicketService ticketService;

    public RevP1Controller(AccountsService accountsService, TicketService ticketService){
        this.accountsService = accountsService;
        this.ticketService = ticketService;
    }

    // Registers a new user
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<Accounts> postNewUser(@RequestBody Accounts accounts) {
        Accounts addedAccount = accountsService.postNewUser(accounts);

        if(addedAccount != null)
            return ResponseEntity.status(200).body(addedAccount);
        else
            return ResponseEntity.status(409).body(addedAccount);
    }

    // Logs a user in
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<Accounts> postUserLogin(@RequestBody Accounts account){
        Accounts loginAccount = accountsService.postUserLogin(account);

        if(loginAccount != null)
            return ResponseEntity.status(200).body(loginAccount);
        else
            return ResponseEntity.status(409).body(loginAccount);
    }

    // Creates a ticket
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/ticket/employee")
    public ResponseEntity<Ticket> postEmployeeTicket(@RequestBody Ticket ticket){
        Ticket addTicket = ticketService.postEmployeeTicket(ticket);

        if(addTicket != null)
            return ResponseEntity.status(200).body(addTicket);
        else
            return ResponseEntity.status(409).body(addTicket);
    }

    // Get ticket by employee username
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/ticket/employee/{username}")
    public ResponseEntity<List<Ticket>> getTicketByUsername(@PathVariable String username) {
        return ResponseEntity.status(200).body(ticketService.getTicketByUsername(username));
    }

    // Get ticket by its status
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/ticket/manager/{status}")
    public ResponseEntity<List<Ticket>> getTicketByStatus(@PathVariable Status status) {
        return ResponseEntity.status(200).body(ticketService.getTicketByStatus(status));
    }

    // Update Tickets
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/ticket/manager/update/{ticketId}")
    public ResponseEntity<Integer> putTicketUpdate(@PathVariable Integer ticketId, @RequestBody Ticket ticket) {
        int val = ticketService.putTicketUpdate(ticketId, ticket.getStatus());
        if(val == 0){
            return ResponseEntity.status(400).body(0);
        }
        else
            return ResponseEntity.status(200).body(1);
    }
    
    
    
}

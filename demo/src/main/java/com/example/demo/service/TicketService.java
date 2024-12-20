package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Ticket;
import com.example.demo.entity.Ticket.Status;
import com.example.demo.repository.TicketRepository;

@Service
public class TicketService {
    private TicketRepository ticketRepo;

    public TicketService(TicketRepository ticketRepo){
        this.ticketRepo = ticketRepo;
    }

    // Ticket from employee going to DB
    public Ticket postEmployeeTicket(Ticket ticket){
        if(ticket.getAmount().isBlank() || ticket.getDescription().isBlank()){
            return null;
        }
        else
            return ticketRepo.save(ticket);
    }

    // Retrieve List of tickets that match username
    public List<Ticket> getTicketByUsername(String username){
        return ticketRepo.findByUsername(username);
    }

    // Retrieve List of tickets that match status
    public List<Ticket> getTicketByStatus(Status status){
        return ticketRepo.findByStatus(status);
    }

    // Update ticket status
    public int putTicketUpdate(int ticketId, Status status){
        Ticket updatedTicket = ticketRepo.findById(ticketId).get();
        updatedTicket.setStatus(status);
        ticketRepo.save(updatedTicket);
        return 1;
    }
}

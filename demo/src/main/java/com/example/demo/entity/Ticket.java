package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="ticket")
public class Ticket {

    public enum Status{
        PENDING,
        APPROVED,
        DENIED
    }
    
    // Creating Id for tickets when created
    @Column(name="ticketId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ticketId;

    // Username column for ticket creator
    private String username;

    // Amount column for ticket
    private String amount;

    // Description of ticket
    private String description;

    // Status of ticket, default is pending status
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    // Constructors for creating ticket instance
    public Ticket(){}

    public Ticket(String username, String amount, String description, Status status){
        this.username = username;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public Ticket(String username, String amount, String description){
        this.username = username;
        this.amount = amount;
        this.description = description;
    }

    public Ticket(Integer ticketId, String username, String amount, String description, Status status){
        this.ticketId = ticketId;
        this.username = username;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

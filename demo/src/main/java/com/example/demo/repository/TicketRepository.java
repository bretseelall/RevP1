package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Ticket;
import com.example.demo.entity.Ticket.Status;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

    List<Ticket> findByUsername(String username);

    List<Ticket> findByStatus(Status status);

}

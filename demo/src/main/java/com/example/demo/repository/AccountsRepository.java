package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Integer>{

    Optional<Accounts> findByUsername(String username);
}

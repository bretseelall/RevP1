package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.repository.TicketRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TicketRegistrationTest {
    ApplicationContext app;
	HttpClient webClient;
	ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws InterruptedException{
        webClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
        String[] args = new String[] {};
        app = SpringApplication.run(DemoApplication.class, args);
        Thread.sleep(500);
    }

    @AfterEach
    public void tearDown() throws InterruptedException{
        Thread.sleep(500);
        SpringApplication.exit(app);
    }

    // Testing ticket registration
    @Test
    public void registerTicketSuccessful() throws IOException, InterruptedException{
        String json = "{\"username\":\"user1\",\"amount\":\"130\",\"description\":\"description\"}";
		HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/ticket/employee")).POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();

		HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
		int status = response.statusCode();
		Assertions.assertEquals(200, status, "Expected Status code 200- Actual Code was: " + status);
    }

    @Test
    public void registerTicketUnsuccessful() throws IOException, InterruptedException{
        String json = "{\"username\":\"user1\",\"amount\":\"\",\"description\":\"description\"}";
		HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/ticket/employee")).POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();

		HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
		int status = response.statusCode();
		Assertions.assertEquals(409, status, "Expected Status code 409- Actual Code was: " + status);
    }
}

package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.entity.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetTicketTests {
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

    //  @Test
    // public void getAllTicketsByUsernameSuccessful() throws IOException, InterruptedException {
    //     HttpRequest request = HttpRequest.newBuilder()
    //             .uri(URI.create("http://localhost:8080/ticket/user1"))
    //             .build();
    //     HttpResponse<String> response = webClient.send(request, HttpResponse.BodyHandlers.ofString());
    //     int status = response.statusCode();
    //     Assertions.assertEquals(200, status, "Expected Status Code 200 - Actual Code was: " + status);
    //     List<Ticket> expectedResult = new ArrayList<Ticket>();
    //     expectedResult.add(new Ticket("user1", "100", "description"));
    //     expectedResult.add(new Ticket("user1", "100", "description"));
    //     List<Ticket> actualResult = objectMapper.readValue(response.body().toString(), new TypeReference<List<Ticket>>(){});
    //     Assertions.assertEquals(expectedResult, actualResult, "Expected="+expectedResult + ", Actual="+actualResult);
}

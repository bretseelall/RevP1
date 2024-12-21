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
import org.mockito.InjectMocks;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.entity.Accounts;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserLoginTest {
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

    // Test for successful login
    @Test
	public void loginUserSuccessful() throws IOException, InterruptedException{
		String json = "{\"username\":\"user1\",\"password\":\"password\"}";
		HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/login")).POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();

		HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
		int status = response.statusCode();
		Assertions.assertEquals(200, status, "Expected Status code 200- Actual Code was: " + status);
        ObjectMapper om = new ObjectMapper();
        Accounts expectedResult = new Accounts("user1", "password");
        Accounts actualResult = om.readValue(response.body().toString(), Accounts.class);
        Assertions.assertEquals(expectedResult, actualResult);
	}

    // Test for login with bad username credentials, should return null
    @Test
	public void loginUserInvalidUser() throws IOException, InterruptedException{
		String json = "{\"username\":\"us\",\"password\":\"password\"}";
		HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/login")).POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();

		HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
		int status = response.statusCode();
		Assertions.assertEquals(409, status, "Expected Status code 409- Actual Code was: " + status);
	}

    // Test for bad user password credentials, should return null
    @Test
	public void loginUserInvalidPass() throws IOException, InterruptedException{
		String json = "{\"username\":\"user1\",\"password\":\"pas\"}";
		HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/login")).POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();

		HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
		int status = response.statusCode();
		Assertions.assertEquals(409, status, "Expected Status code 409- Actual Code was: " + status);
	}
}

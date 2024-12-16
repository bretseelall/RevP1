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

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserRegistrationTest {
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

	// Testing new user registration success
	@Test
	public void registerUserSuccessful() throws IOException, InterruptedException{
		String json = "{\"username\":\"user1\",\"password\":\"password\"}";
		HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/register")).POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();

		HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
		int status = response.statusCode();
		Assertions.assertEquals(200, status, "Expected Status code 200- Actual Code was: " + status);
	}

    // Test to see if username is blank, it should return null throwing 409 status code
    @Test
	public void registerUserBlankUsername() throws IOException, InterruptedException{
		String json = "{\"username\":\"\",\"password\":\"password\"}";
		HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/register")).POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();

		HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
		int status = response.statusCode();
		Assertions.assertEquals(409, status, "Expected Status code 409- Actual Code was: " + status);
	}

    // Test to see if password field is blank, should return 409 Error
    @Test
	public void registerUserBlankPassword() throws IOException, InterruptedException{
		String json = "{\"username\":\"username\",\"password\":\"\"}";
		HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/register")).POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();

		HttpResponse<String> response = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
		int status = response.statusCode();
		Assertions.assertEquals(409, status, "Expected Status code 409- Actual Code was: " + status);
	}

    // Test to see if username is already taken, then return 409 Error
    @Test
	public void registerUserExistingUsername() throws IOException, InterruptedException{
		String json = "{\"username\":\"user2\",\"password\":\"password\"}";
		HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/register")).POST(HttpRequest.BodyPublishers.ofString(json)).header("Content-Type", "application/json").build();

		HttpResponse<String> response1 = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response2 = webClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
		int status1 = response1.statusCode();
        int status2 = response2.statusCode();
        Assertions.assertEquals(200, status1, "Expected Status code 200- Actual Code was: " + status1);
		Assertions.assertEquals(409, status2, "Expected Status code 409- Actual Code was: " + status2);
	}

}

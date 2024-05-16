package com.aryak.product.controller;

import com.aryak.product.dto.RecaptchaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class LoginController {

    @Value("${google.recaptcha.secret.key}")
    private String secretKey;

    @Value("${google.recaptcha.verify.url}")
    private String url;

    @GetMapping("/")
    public String showLogin() {
        return "index.jsp";
    }

    @PostMapping("/login")
    public String process(@RequestParam(name = "g-recaptcha-response") String response) throws IOException, InterruptedException {
        // Initialize HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();
        String requestBody = "?secret=" + secretKey + "&response=" + response;

        // Build the request
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url + requestBody))
                //.header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Send the request and handle the response
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response code: " + httpResponse.statusCode());
            System.out.println("Response body: " + httpResponse.body());
            var recaptchaResponse = objectMapper.readValue(httpResponse.body(), RecaptchaResponse.class);
            System.out.println(recaptchaResponse);
            return recaptchaResponse.success() ? "success.jsp" : "index.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            return "index.jsp";
        }
    }
}

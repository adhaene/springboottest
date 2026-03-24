package com.bezkoder.spring.jpa.h2.rest;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/*
curl http://localhost:8080/hello
curl -X POST http://localhost:8080/echo -d "Test message"
 */
public class SimpleRestApi {

    public static void main(String[] args) {
        try {
            // Create HTTP server on port 8080
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            // Register endpoints
            server.createContext("/hello", new HelloHandler());
            server.createContext("/echo", new EchoHandler());

            // Start the server
            server.setExecutor(null); // Default executor
            System.out.println("Server started on http://localhost:8080");
            server.start();

        } catch (IOException e) {
            System.err.println("Server failed to start: " + e.getMessage());
        }
    }

    // GET /hello
    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "Method Not Allowed");
                return;
            }
            sendResponse(exchange, 200, "{\"message\":\"Hello, World!\"}");
        }
    }

    // POST /echo
    static class EchoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "Method Not Allowed");
                return;
            }

            // Read request body
            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            sendResponse(exchange, 200, "{\"echo\":\"" + body.replace("\"", "\\\"") + "\"}");
        }
    }

    // Utility method to send JSON responses
    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}


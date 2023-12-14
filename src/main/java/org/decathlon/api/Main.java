package org.decathlon.api;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.decathlon.api.Event.QueryParams;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

enum HttpMethod {
    GET, OPTIONS
}

public class Main {

    private static final int PORT = 8080;
    private static final String CLIENT_URL = "http://localhost:3000";

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        server.createContext("/api/score", exchange -> {
            HttpMethod method = HttpMethod.valueOf(exchange.getRequestMethod());

            if (method == HttpMethod.OPTIONS) {
                handleCors(exchange);
                exchange.sendResponseHeaders(200, -1);
            } else if (method == HttpMethod.GET) {
                setCommonResponseHeaders(exchange);

                var queryParams = new QueryParams(exchange.getRequestURI());
                var result = queryParams.getResult();
                var score = queryParams.getEvent().getScore(result);

                sendJsonResponse(exchange, String.format("{\"score\": \"%s\"}", score));
            } else {
                exchange.sendResponseHeaders(400, -1);
            }

            exchange.close();
        });

        server.setExecutor(null); // creates a default executor
        server.start();
    }

    private static void handleCors(HttpExchange exchange) {
        Headers headers = exchange.getResponseHeaders();
        headers.set("Access-Control-Allow-Origin", CLIENT_URL);
        headers.set("Access-Control-Allow-Methods", "GET");
        headers.set("Access-Control-Allow-Headers", "Content-Type");
        headers.set("Access-Control-Max-Age", "86400");
    }

    private static void setCommonResponseHeaders(HttpExchange exchange) {
        Headers headers = exchange.getResponseHeaders();
        headers.set("Access-Control-Allow-Origin", CLIENT_URL);
        headers.set("Access-Control-Allow-Methods", "GET");
    }

    private static void sendJsonResponse(HttpExchange exchange, String jsonResponse) throws IOException {
        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", "application/json");

        exchange.sendResponseHeaders(200, 0);
        OutputStream output = exchange.getResponseBody();
        output.write(jsonResponse.getBytes());
        output.flush();
    }
}

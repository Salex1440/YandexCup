package com.salex.year2019.phone_number_validator;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class PhoneNumberValidatorServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(7777), 0);
        server.createContext("/test", new MyHandler());
        server.createContext("/ping", new PingHandler());
        server.createContext("/shutdown", new ShutdownHandler(server));
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class PingHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            System.out.println("PING");
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write("".getBytes());
            os.close();
        }
    }

    static class ShutdownHandler implements HttpHandler {
        private HttpServer server;

        public ShutdownHandler(HttpServer server) {
            this.server = server;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write("".getBytes());
            os.close();
            System.out.println("Server shutdown");
            server.stop(0);
        }
    }

}

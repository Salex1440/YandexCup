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
        server.createContext("/validatePhoneNumber", new ValidatePhoneNumberHandler());
        server.createContext("/ping", new PingHandler());
        server.createContext("/shutdown", new ShutdownHandler(server));
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started");
    }

    static class ValidatePhoneNumberHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            if (!validateQuery(query)) {
                exchange.sendResponseHeaders(400, 0);
                return;
            }
            String phoneNumber = query.substring(13);
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(phoneNumber.getBytes());
            os.close();
        }

        private boolean validateQuery(String query) {
            if (query == null) return false;
            String[] args = query.split("&");
            if (args.length != 1) return false;
            String[] split = args[0].split("=");
            if (split.length != 2) return false;
            if (!split[0].equals("phone_number")) return false;
            return true;
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

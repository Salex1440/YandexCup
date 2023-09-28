package com.salex.year2019.phone_number_validator;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.regex.Pattern;

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
                exchange.getResponseBody().close();
                return;
            }
            String phoneNumber = query.substring(13);
            if (!validatePhoneNumber(phoneNumber)) {
                exchange.sendResponseHeaders(400, 0);
                exchange.getResponseBody().close();
                return;
            }
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

        private boolean validatePhoneNumber(String number) {
            Pattern pattern1 = Pattern.compile("^8((?:982)|(?:986)|(?:912)|(?:934))\\d{7}$");
            Pattern pattern2 = Pattern.compile("^\\+7((?:982)|(?:986)|(?:912)|(?:934))\\d{7}$");
            Pattern pattern3 = Pattern.compile("^8\\(((?:982)|(?:986)|(?:912)|(?:934))\\)\\d{3}-\\d{4}$");
            Pattern pattern4 = Pattern.compile("^\\+7\\(((?:982)|(?:986)|(?:912)|(?:934))\\)\\d{3}-\\d{4}$");
            Pattern pattern5 = Pattern.compile("^8\\s((?:982)|(?:986)|(?:912)|(?:934))\\s\\d{3}\\s\\d{2}\\s\\d{2}$");
            Pattern pattern6 = Pattern.compile("^\\+7\\s((?:982)|(?:986)|(?:912)|(?:934))\\s\\d{3}\\s\\d{2}\\s\\d{2}$");
            Pattern pattern7 = Pattern.compile("^8\\s((?:982)|(?:986)|(?:912)|(?:934))\\s\\d{3}\\s\\d{4}$");
            Pattern pattern8 = Pattern.compile("^\\+7\\s((?:982)|(?:986)|(?:912)|(?:934))\\s\\d{3}\\s\\d{4}$");

            return  pattern1.matcher(number).matches() ||
                    pattern2.matcher(number).matches() ||
                    pattern3.matcher(number).matches() ||
                    pattern4.matcher(number).matches() ||
                    pattern5.matcher(number).matches() ||
                    pattern6.matcher(number).matches() ||
                    pattern7.matcher(number).matches() ||
                    pattern8.matcher(number).matches();
        }

        private class Success {
            private boolean status;
            private String normalized;

            public Success(boolean status, String normalized) {
                this.status = status;
                this.normalized = normalized;
            }
        }

        private class Error {
            private boolean status;

            public Error(boolean status) {
                this.status = status;
            }
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

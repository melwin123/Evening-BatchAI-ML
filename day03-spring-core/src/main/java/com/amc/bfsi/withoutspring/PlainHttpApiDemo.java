package com.amc.bfsi.withoutspring;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A REST endpoint with NO framework at all - only the JDK.
 * Everything Spring does for you, done by hand:
 *   routing, reading the body, parsing JSON, validating, status codes, writing JSON.
 */
public class PlainHttpApiDemo {

    // ---------- the "entity" ----------
    static class Customer {
        int id; String name; String email; String city;
        Customer(int id, String name, String email, String city) {
            this.id = id; this.name = name; this.email = email; this.city = city;
        }
        String toJson() {
            return "{\"customerId\":" + id + ",\"name\":\"" + name
                 + "\",\"email\":\"" + email + "\",\"city\":\"" + city + "\"}";
        }
    }

    // ---------- the "repository" ----------
    static final Map<Integer, Customer> STORE = new LinkedHashMap<>();
    static final AtomicInteger SEQ = new AtomicInteger(100);

    // ---------- JSON by hand (a real parser is hundreds of lines) ----------
    static String field(String json, String key) {
        int k = json.indexOf("\"" + key + "\"");
        if (k < 0) return null;
        int colon = json.indexOf(':', k);
        int q1 = json.indexOf('"', colon + 1);
        int q2 = json.indexOf('"', q1 + 1);
        return (q1 < 0 || q2 < 0) ? null : json.substring(q1 + 1, q2);
    }

    static void send(HttpExchange ex, int status, String json) throws IOException {
        byte[] body = json.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().add("Content-Type", "application/json");
        ex.sendResponseHeaders(status, body.length);
        try (OutputStream os = ex.getResponseBody()) { os.write(body); }
    }

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8099), 0);

        server.createContext("/api/customers", ex -> {
            String method = ex.getRequestMethod();
            String path = ex.getRequestURI().getPath();

            // ---------- routing, by hand ----------
            if ("POST".equals(method) && path.equals("/api/customers")) {
                String body = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);

                String name = field(body, "name");
                String email = field(body, "email");
                String city = field(body, "city");

                // ---------- validation, by hand ----------
                List<String> errors = new ArrayList<>();
                if (name == null || name.trim().length() < 3) errors.add("\"name\":\"at least 3 characters\"");
                if (email == null || !email.contains("@"))    errors.add("\"email\":\"not a valid email\"");
                if (city == null || city.isBlank())           errors.add("\"city\":\"is required\"");
                if (!errors.isEmpty()) {
                    send(ex, 400, "{\"status\":400,\"message\":\"Validation failed\",\"errors\":{"
                            + String.join(",", errors) + "}}");
                    return;
                }
                // ---------- duplicate rule, by hand ----------
                for (Customer c : STORE.values()) {
                    if (c.email.equalsIgnoreCase(email)) {
                        send(ex, 409, "{\"status\":409,\"message\":\"Email already exists\"}");
                        return;
                    }
                }
                Customer saved = new Customer(SEQ.incrementAndGet(), name, email, city);
                STORE.put(saved.id, saved);
                send(ex, 201, saved.toJson());
                return;
            }

            if ("GET".equals(method) && path.equals("/api/customers")) {
                StringJoiner all = new StringJoiner(",", "[", "]");
                STORE.values().forEach(c -> all.add(c.toJson()));
                send(ex, 200, all.toString());
                return;
            }

            if ("GET".equals(method) && path.startsWith("/api/customers/")) {
                String idText = path.substring("/api/customers/".length());
                try {
                    Customer c = STORE.get(Integer.parseInt(idText));       // path variable, by hand
                    if (c == null) {
                        send(ex, 404, "{\"status\":404,\"message\":\"No customer " + idText + "\"}");
                    } else {
                        send(ex, 200, c.toJson());
                    }
                } catch (NumberFormatException e) {
                    send(ex, 400, "{\"status\":400,\"message\":\"id must be a number\"}");
                }
                return;
            }

            send(ex, 405, "{\"status\":405,\"message\":\"Method not allowed\"}");
        });

        server.start();
        System.out.println("Plain JDK server started on http://localhost:8099");

        // ---------- call it, so the demo is self-contained ----------
        HttpClient client = HttpClient.newHttpClient();
        call(client, "POST", "/api/customers", "{\"name\":\"Anita Rao\",\"email\":\"anita@example.com\",\"city\":\"Bangalore\"}");
        call(client, "POST", "/api/customers", "{\"name\":\"Ab\",\"email\":\"not-an-email\",\"city\":\"\"}");
        call(client, "POST", "/api/customers", "{\"name\":\"Anita Rao\",\"email\":\"anita@example.com\",\"city\":\"Bangalore\"}");
        call(client, "GET", "/api/customers", null);
        call(client, "GET", "/api/customers/9999", null);

        server.stop(0);
        System.out.println();
        System.out.println("That is 1 endpoint, 3 routes, no database, no security, no tests -");
        System.out.println("and every line of routing, JSON and validation written by hand.");
    }

    static void call(HttpClient client, String method, String path, String body) throws Exception {
        HttpRequest.Builder b = HttpRequest.newBuilder(URI.create("http://localhost:8099" + path))
                .header("Content-Type", "application/json");
        b = body == null ? b.GET() : b.POST(HttpRequest.BodyPublishers.ofString(body));
        HttpResponse<String> r = client.send(b.build(), HttpResponse.BodyHandlers.ofString());
        System.out.println(method + " " + path + "  ->  " + r.statusCode() + "  " + r.body());
    }
}
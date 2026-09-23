package com.amc.bfsi.withoutspring;

import java.util.*;

/**
 * WITHOUT SPRING - the whole application wired by hand.
 * Six objects, and main() has to know how to build every one of them, in the right order.
 */
public class ManualWiringDemo {

    // ---------------- configuration ----------------
    static class AppProperties {
        private final Map<String, String> values = new HashMap<>();
        AppProperties() {
            // in a real app: read application.properties, handle missing file, missing key, type conversion
            values.put("bank.name", "AMC Bank");
            values.put("audit.enabled", "true");
            System.out.println("  built AppProperties");
        }
        String get(String key) { return values.get(key); }
        boolean getBoolean(String key) { return Boolean.parseBoolean(values.get(key)); }
    }

    // ---------------- infrastructure ----------------
    static class AuditLogger {
        private final boolean enabled;
        AuditLogger(AppProperties props) {
            this.enabled = props.getBoolean("audit.enabled");
            System.out.println("  built AuditLogger (enabled=" + enabled + ")");
        }
        void record(String what) { if (enabled) System.out.println("     audit: " + what); }
    }

    static class NotificationService {
        private final String bankName;
        NotificationService(AppProperties props) {
            this.bankName = props.get("bank.name");
            System.out.println("  built NotificationService");
        }
        void welcome(String customer) { System.out.println("     sms: Welcome to " + bankName + ", " + customer); }
    }

    // ---------------- data ----------------
    interface CustomerRepository { void save(String name); List<String> findAll(); }

    static class InMemoryCustomerRepository implements CustomerRepository {
        private final List<String> rows = new ArrayList<>();
        InMemoryCustomerRepository() { System.out.println("  built InMemoryCustomerRepository"); }
        public void save(String name) { rows.add(name); }
        public List<String> findAll() { return rows; }
    }

    // ---------------- business ----------------
    static class CustomerService {
        private final CustomerRepository repository;
        private final AuditLogger audit;
        private final NotificationService notifier;

        CustomerService(CustomerRepository repository, AuditLogger audit, NotificationService notifier) {
            this.repository = repository;
            this.audit = audit;
            this.notifier = notifier;
            System.out.println("  built CustomerService");
        }

        void register(String name) {
            repository.save(name);
            audit.record("registered " + name);
            notifier.welcome(name);
        }
    }

    public static void main(String[] args) {

        System.out.println("WIRING BY HAND (this is the part Spring removes)");
        AppProperties props = new AppProperties();                       // 1
        AuditLogger audit = new AuditLogger(props);                      // 2  needs 1
        NotificationService notifier = new NotificationService(props);   // 3  needs 1
        CustomerRepository repository = new InMemoryCustomerRepository();// 4
        CustomerService service = new CustomerService(repository, audit, notifier);   // 5 needs 2,3,4

        System.out.println("USING THE APPLICATION");
        service.register("Anita Rao");

        System.out.println();
        System.out.println("Count what main() had to know:");
        System.out.println("  - every class name and its constructor");
        System.out.println("  - the ORDER: properties before audit, audit before service");
        System.out.println("  - that only ONE AppProperties should exist and be shared");
        System.out.println("  - when to close things, if any of them held a file or a connection");
        System.out.println();
        System.out.println("Add a sixth object that CustomerService needs, and this method changes again.");
        System.out.println("In a real project this file grows to hundreds of lines and every team edits it.");
    }
}
package com.amc.bfsi.withoutspring;

import java.util.*;

/**
 * WITHOUT SPRING - two problems that appear once the app is more than a demo:
 * accidental duplicates, and lifecycle you must run yourself.
 */
public class SharedInstanceProblemDemo {

    static class ConnectionPool implements AutoCloseable {
        private static int instances = 0;
        private final int id;
        ConnectionPool() {
            id = ++instances;
            System.out.println("  OPENED connection pool #" + id + "  (expensive!)");
        }
        void query(String sql) { System.out.println("     pool#" + id + " runs " + sql); }
        @Override public void close() { System.out.println("  CLOSED connection pool #" + id); }
        static int instances() { return instances; }
    }

    static class CustomerRepository {
        private final ConnectionPool pool;
        CustomerRepository(ConnectionPool pool) { this.pool = pool; }
        void save(String name) { pool.query("INSERT customer " + name); }
    }

    static class LoanRepository {
        private final ConnectionPool pool;
        LoanRepository(ConnectionPool pool) { this.pool = pool; }
        void save(String type) { pool.query("INSERT loan " + type); }
    }

    public static void main(String[] args) {

        System.out.println("MISTAKE: each repository builds its own pool");
        CustomerRepository r1 = new CustomerRepository(new ConnectionPool());
        LoanRepository r2 = new LoanRepository(new ConnectionPool());
        r1.save("Anita");
        r2.save("HOME");
        System.out.println("  pools created: " + ConnectionPool.instances() + "  <- should have been 1");

        System.out.println();
        System.out.println("FIX BY HAND: build one, pass it to both, remember to close it");
        try (ConnectionPool shared = new ConnectionPool()) {
            CustomerRepository r3 = new CustomerRepository(shared);
            LoanRepository r4 = new LoanRepository(shared);
            r3.save("Vikram");
            r4.save("CAR");
        }   // close() runs here - and you had to remember it

        System.out.println();
        System.out.println("Spring does both for you: singleton scope means one pool,");
        System.out.println("and @PreDestroy / destroyMethod closes it when the context closes.");
    }
}
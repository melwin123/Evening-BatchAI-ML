package com.amc.bfsi.withoutspring;

import java.io.*;
import java.util.*;

/** Reading settings without Spring: you load, parse, convert and default - every time. */
public class ManualConfigDemo {

    static class AppSettings {
        final String bankName; 
        final int port; 
        final boolean seedData; 
        final double savingsRate;

        AppSettings(Properties p) {
            // every value: read, default, convert, and hope nobody typos the key
            this.bankName    = p.getProperty("bank.name", "Unknown Bank");
            this.port        = Integer.parseInt(p.getProperty("server.port", "8080"));
            this.seedData    = Boolean.parseBoolean(p.getProperty("app.seed.enabled", "false"));
            this.savingsRate = Double.parseDouble(p.getProperty("bank.savings-rate", "0.0"));
        }
    }

    public static void main(String[] args) throws IOException {
        // pretend this file sits on disk
        String fileContents = "bank.name=AMC Bank\nserver.port=9090\napp.seed.enabled=true\nbank.savings-rate=4.0\n";
        File file = File.createTempFile("application", ".properties");
        try (Writer w = new FileWriter(file)) { w.write(fileContents); }

        Properties props = new Properties();
        try (InputStream in = new FileInputStream(file)) {   // load it yourself
            props.load(in);
        } catch (IOException e) {
            System.out.println("Could not read config: " + e.getMessage());
            return;
        }

        AppSettings settings = new AppSettings(props);
        System.out.println("bank name    : " + settings.bankName);
        System.out.println("port         : " + settings.port);
        System.out.println("seed data    : " + settings.seedData);
        System.out.println("savings rate : " + settings.savingsRate);

        // a missing key is only discovered when the line runs
        try {
            int timeout = Integer.parseInt(props.getProperty("app.timeout"));
            System.out.println("timeout      : " + timeout);
        } catch (NumberFormatException e) {
            System.out.println("timeout      : app.timeout was never set - NumberFormatException at run time");
        }

        file.delete();
        System.out.println();
       // System.out.println("No profiles, no environment overrides, no type checking, no validation.");
       // System.out.println("Add dev/test/prod and you write all of that yourself.");
    }
}

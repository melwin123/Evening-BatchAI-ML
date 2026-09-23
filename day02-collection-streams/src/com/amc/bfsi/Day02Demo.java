package com.amc.bfsi;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day02Demo {

    public static void main(String[] args) {

        CustomerRepository repo = new CustomerRepository();
        repo.save(new Customer(101, "Anita Rao", "Bangalore", 52000));
        repo.save(new Customer(102, "Vikram ", "Mumbai", 18000));
        repo.save(new Customer(103, "Rahul ", "Bangalore", 96000));
        repo.save(new Customer(104, "Sneha ", "Chennai", 7000));

        // try - catch - finally with a checked exception
        try {
            System.out.println("Found: " + repo.findById(102));
            System.out.println("Found: " + repo.findById(999));
        } catch (CustomerNotFoundException e) {
            System.out.println("Handled: " + e.getMessage());
        } finally {
            System.out.println("Lookup attempt finished");
        }

        List<Customer> all = repo.findAll();

        // Comparator - sort by balance, highest first
        all.sort(Comparator.comparingDouble(Customer::getBalance).reversed());
        System.out.println("\nBy balance (highest first):");
        all.forEach(System.out::println);

        // filter + map + collect
        List<String> richNames = all.stream()
                .filter(c -> c.getBalance() > 20000)
                .map(Customer::getName)
                .collect(Collectors.toList());
        System.out.println("\nBalance above 20000: " + richNames);

        // grouping
        Map<String, List<Customer>> byCity = all.stream()
                .collect(Collectors.groupingBy(Customer::getCity));
        System.out.println("\nGrouped by city: " + byCity.keySet());

        // reduce / summary
        double total = all.stream().mapToDouble(Customer::getBalance).sum();
        System.out.printf("Total deposits: %.2f%n", total);

        // Optional - no more null checks
        Optional<Customer> top = all.stream().max(Comparator.comparingDouble(Customer::getBalance));
        top.ifPresent(c -> System.out.println("Top customer: " + c.getName()));

        // unchecked exception
        try {
            withdraw(5000, -100);
        } catch (InvalidAmountException e) {
            System.out.println("Handled: " + e.getMessage());
        }
    }

    static double withdraw(double balance, double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }
        return balance - amount;
    }
}

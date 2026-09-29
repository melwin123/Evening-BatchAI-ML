package com.amc.bfsi.config;

import com.amc.bfsi.entity.*;
import com.amc.bfsi.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Puts three users and a handful of customers in place so the app is demo-ready. */
@Component
public class DataSeeder implements CommandLineRunner {

    private final AppUserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;

    @Value("${app.seed.enabled:true}")
    private boolean enabled;

    public DataSeeder(AppUserRepository userRepository, CustomerRepository customerRepository,
                      AccountRepository accountRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (!enabled) {
            return;
        }
        seedUsers();
        seedCustomers();
    }

    private void seedUsers() {
        addUser("admin", "admin123", "ADMIN");
        addUser("officer", "officer123", "OFFICER");
        addUser("clerk", "clerk123", "USER");
    }

    private void addUser(String username, String rawPassword, String role) {
        if (!userRepository.existsByUsername(username)) {
            userRepository.save(new AppUser(username, encoder.encode(rawPassword), role));
        }
    }

    private void seedCustomers() {
        if (customerRepository.count() > 0) {
            return;
        }

        Customer anita = new Customer("Anita ", "anita@example.com", "Bangalore", "ABCDE1234F");
        anita.addAccount(new Account(AccountType.SAVINGS, 52000.0));
        anita.addAccount(new Account(AccountType.CURRENT, 130000.0));

        Customer vikram = new Customer("Vikram ", "vikram@example.com", "Mumbai", "BCDEF2345G");
        vikram.addAccount(new Account(AccountType.SAVINGS, 18000.0));

        Customer rahul = new Customer("Rahul ", "rahul@example.com", "Bangalore", "CDEFG3456H");
        rahul.addAccount(new Account(AccountType.SAVINGS, 96000.0));

        Customer sneha = new Customer("Sneha ", "sneha@example.com", "Chennai", "DEFGH4567I");

        customerRepository.saveAll(java.util.List.of(anita, vikram, rahul, sneha));

        // give every seeded account its display number
        accountRepository.findAll().forEach(a -> {
            if (a.getAccountNumber() == null) {
                a.setAccountNumber("AC" + (5000 + a.getAccountId()));
                accountRepository.save(a);
            }
        });
    }
}

package com.amc.bfsi.service;

import com.amc.bfsi.model.Customer;
import com.amc.bfsi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    /**
     * Constructor injection - the container passes the repository in.
     * The service never calls "new", so it can be tested with a fake repository.
     */
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void register(Customer customer) {
        repository.save(customer);
    }

    public Customer getById(int id) {
        return repository.findById(id);
    }

    public List<Customer> getAll() {
        return repository.findAll();
    }
}
